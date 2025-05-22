package co.edu.uniquindio.bookyourstay.servicios;

import co.edu.uniquindio.bookyourstay.modelo.*;
import co.edu.uniquindio.bookyourstay.modelo.factory.Alojamiento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Apartamento;
import co.edu.uniquindio.bookyourstay.modelo.factory.Casa;
import co.edu.uniquindio.bookyourstay.repositorios.AlojamientoRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.ReseniaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.ReservaRepositorio;
import co.edu.uniquindio.bookyourstay.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.bookyourstay.util.EmailUtil;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;



public class ReservaService {

    private final ReservaRepositorio reservaRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final BilleteraService billeteraService;

    public ReservaService(ReservaRepositorio reservaRepositorio, UsuarioRepositorio usuarioRepositorio, BilleteraService billeteraService) {
        this.reservaRepositorio = reservaRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.billeteraService = billeteraService;
    }

    /**
     * metodo que valida disponibilidad del alojamiento
     * @param alojamiento
     * @param inicio
     * @param fin
     * @return
     */
    public boolean validarDisponibilidad(Alojamiento alojamiento, LocalDate inicio, LocalDate fin) {
        List<Reserva> reservas = reservaRepositorio.listar();

        for (Reserva r : reservas) {
            if (r.getAlojamiento().getId().equals(alojamiento.getId())) {
                boolean seSolapan = !(fin.isBefore(r.getFechaInicio()) || inicio.isAfter(r.getFechaFin()));
                if (seSolapan) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * metodo que valida la capacidad del alojamiento
     * @param alojamiento
     * @param cantidad
     * @return
     */
    public boolean validarCapacidad(Alojamiento alojamiento, int cantidad) {
        return alojamiento.getCapacidadMaxima() >= cantidad;
    }

    /**
     * metodo que realiza reservas
     * @param cedulaCliente
     * @param alojamiento
     * @param inicio
     * @param fin
     * @param huespedes
     * @return
     * @throws Exception
     */
    public Factura realizarReserva(String cedulaCliente, Alojamiento alojamiento, LocalDate inicio, LocalDate fin, int huespedes) throws Exception {
        Cliente cliente = (Cliente) usuarioRepositorio.obtenerPorCedula(cedulaCliente);

        if (cliente == null) {
            throw new Exception("El cliente no existe.");
        }

        if (!validarCapacidad(alojamiento, huespedes)) {
            throw new Exception("El alojamiento no soporta esa cantidad de huéspedes.");
        }

        if (!validarDisponibilidad(alojamiento, inicio, fin)) {
            throw new Exception("El alojamiento no está disponible en esas fechas.");
        }

        if (fin.isBefore(inicio) || fin.isEqual(inicio)) {
            throw new Exception("La fecha de fin debe ser posterior a la de inicio.");
        }

        String id = String.valueOf((int) (Math.random() * 9000) + 1000); // Genera un número entre 1000 y 9999


        Reserva reservaTemporal = Reserva.builder()
                .id(String.valueOf((int)(Math.random() * 9000) + 1000))
                .cliente(cliente)
                .alojamiento(alojamiento)
                .fechaInicio(inicio)
                .fechaFin(fin)
                .numHuespedes(huespedes)
                .factura(null)
                .build();

        Factura factura = generarFactura(reservaTemporal);

        // Procesar el pago a través de billeteraService
        billeteraService.procesarPago(cedulaCliente, factura.getTotal());

        // Si no lanza excepción, continuar con la reserva
        reservaTemporal.setFactura(factura);
        reservaRepositorio.agregar(reservaTemporal);

        String mensaje = cliente.getNombre()
                + "\nAlojamiento: " + alojamiento.getNombre()
                + "\nCiudad: " + alojamiento.getCiudad()
                + "\nDesde: " + inicio
                + "\nHasta: " + fin
                + "\nSubtotal: $" + factura.getSubtotal()
                + "\nTotal: $" + factura.getTotal();

        EmailUtil.enviarNotificacion(cliente.getEmail(), "Se ha realizado una reserva", mensaje);

        return factura;
    }

    /**
     * metodo que cancela reservas
     * @param reserva
     */
    public void cancelarReserva(Reserva reserva) {
        reservaRepositorio.eliminar(reserva);
    }

    /**
     * metodo que genera facturas
     * @param reserva
     * @return
     */
    public Factura generarFactura(Reserva reserva) {
        float subtotal = calcularSubtotal(reserva);
        float total = subtotal;
        String id = UUID.randomUUID().toString();
        LocalDate fecha = LocalDate.now();

        return new Factura(id, fecha, subtotal, total);
    }

    /**
     * metodo que calcula subtotal
     * @param reserva
     * @return
     */
    private float calcularSubtotal(Reserva reserva) {
        Alojamiento alojamiento = reserva.getAlojamiento();
        LocalDate inicio = reserva.getFechaInicio();
        LocalDate fin = reserva.getFechaFin();

        long dias = ChronoUnit.DAYS.between(inicio, fin);
        if (dias == 0) dias = 1;

        float subtotal = dias * alojamiento.getPrecioNoche();

        if (alojamiento instanceof Casa || alojamiento instanceof Apartamento) {
            subtotal += 50000; // Costo adicional fijo por limpieza
        }

        return subtotal;
    }

    /**
     * metodo que obtiene los alojamientos mas rentables
     * @return
     */
    public Map<String, Double> obtenerTiposAlojamientoMasRentables() {
        List<Reserva> reservas = reservaRepositorio.listar();
        Map<String, Double> ingresosPorTipo = new HashMap<>();

        for (Reserva reserva : reservas) {
            Alojamiento alojamiento = reserva.getAlojamiento();
            String tipo = alojamiento.getTipo();
            double ingreso = reserva.getFactura() != null ? reserva.getFactura().getTotal() : 0;

            ingresosPorTipo.put(tipo, ingresosPorTipo.getOrDefault(tipo, 0.0) + ingreso);
        }

        return ingresosPorTipo.entrySet().stream()
                .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


}
