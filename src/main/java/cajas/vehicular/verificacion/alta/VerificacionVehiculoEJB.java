package cajas.vehicular.verificacion.alta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import cajas.exception.BusinessException;
import cajas.persistence.entity.VerificacionVehicularEntity;
import cajas.persistence.query.VerificacionVehicularQuery;
import cajas.seguridad.usuario.Usuario;
import cajas.vehicular.verificacion.alta.CrearVerificacionVehiculo;
import cajas.util.Crypto;

@Stateless
public class VerificacionVehiculoEJB {
	
	@Inject
	VerificacionVehicularQuery vVehicularQuery; 
	
	@PersistenceContext(name = "sitDS")
	private EntityManager entityManager;

	public void crearVerificacionVehiculoMetodo(VerificacionVehiculo verificacionVehiculo) {
		try {
			
			VerificacionVehicularEntity verificacionVehiculoEntity = new VerificacionVehicularEntity();
			verificacionVehiculoEntity.setIdOficinaVerificacion(verificacionVehiculo.getIdOficinaVerificacion());
			verificacionVehiculoEntity.setEjercicio(verificacionVehiculo.getEjercicio());
			verificacionVehiculoEntity.setTipoVerificacion(verificacionVehiculo.getTipoVerificacion());
			verificacionVehiculoEntity.setEstatusVerificacion(verificacionVehiculo.getEstatusVerificacion());
			verificacionVehiculoEntity.setFechaVerificacion(verificacionVehiculo.getFechaVerificacion());
			verificacionVehiculoEntity.setVinVehiculo(verificacionVehiculo.getVinVehiculo());
			verificacionVehiculoEntity.setNumeroMotorVehiculo(verificacionVehiculo.getNumeroMotorVehiculo());
			verificacionVehiculoEntity.setIdMarcaVehiculo(verificacionVehiculo.getIdMarcaVehiculo());
			verificacionVehiculoEntity.setModeloVehiculo(verificacionVehiculo.getModeloVehiculo());
			verificacionVehiculoEntity.setIdClaseVehiculo(verificacionVehiculo.getIdClaseVehiculo());
			verificacionVehiculoEntity.setIdTipoVehiculo(verificacionVehiculo.getIdTipoVehiculo());
			verificacionVehiculoEntity.setLineaVehiculo(verificacionVehiculo.getLineaVehiculo());
			verificacionVehiculoEntity.setNombrePersonaVerificacion(verificacionVehiculo.getNombrePersonaVerificacion());
			verificacionVehiculoEntity.setApellidoPaternoPersonaVerificacion(verificacionVehiculo.getApellidoPaternoPersonaVerificacion());
			verificacionVehiculoEntity.setApellidoMaternoPersonaVerificacion(verificacionVehiculo.getApellidoMaternoPersonaVerificacion());
			verificacionVehiculoEntity.setFacturaVehiculoDocumentacion((Integer)verificacionVehiculo.getFacturaVehiculoDocumentacion());
			verificacionVehiculoEntity.setIdentificacionOficialDocumentacion((Integer)verificacionVehiculo.getIdentificacionOficialDocumentacion());
			verificacionVehiculoEntity.setIdentificacionRepresentanteLegalDocumentacion((Integer)verificacionVehiculo.getIdentificacionRepresentanteLegalDocumentacion());
			verificacionVehiculoEntity.setComprobanteDomicilioDocumentacion((Integer)verificacionVehiculo.getComprobanteDomicilioDocumentacion());
			verificacionVehiculoEntity.setRfcPersonaMoralDocumentacion((Integer)verificacionVehiculo.getRfcPersonaMoralDocumentacion());
			verificacionVehiculoEntity.setAnioActualComprobantePago((Integer)verificacionVehiculo.getAnioActualComprobantePago());	
			vVehicularQuery.registrarVerificacion(verificacionVehiculoEntity);
		} catch (PersistenceException ex) {
			ex.printStackTrace();
			throw new BusinessException("Ocurrio un problema al registrar al registrar la Verificación del Vehiculo.");
		}
	}
	
	/********Obtener VIN *********/
	public VerificacionVehiculo obtenerVin(String vin) {
		try {
			VerificacionVehicularEntity vVehiculoEntity = vVehicularQuery.consultar(vin);
			VerificacionVehiculo vVehiculo = new VerificacionVehiculo();
			vVehiculo = vVehiculo.verificacionVehiculoEntity(vVehiculoEntity);
			return vVehiculo;
		} catch (NoResultException ex) {
			throw new BusinessException("El Vin no existe.");
		}
	}


}
