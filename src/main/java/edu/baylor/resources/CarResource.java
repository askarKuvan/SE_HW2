/***************************************
 *
 * Author       : Askar Kuvanychbekov
 * Assignment   : II - mini app
 * Class        : CSI 5354
 *
 ***************************************/

package edu.baylor.resources;

import edu.baylor.model.Car;
import edu.baylor.model.Person;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;

@Path("/car")
@ApplicationScoped
public class CarResource {

    @PersistenceContext(unitName = "CarsPU")
    private EntityManager em;

    @Inject
    PersonResource personResource;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Car> all() throws Exception {
        return em.createNamedQuery("Car.findAll", Car.class)
                .getResultList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{carId}")
    public Car get(@PathParam("carId") Integer carId) {
        return em.find(Car.class, carId);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{carId}")
    @Transactional
    public Response remove(@PathParam("carId") Integer carId) throws Exception {
        try {
            Car entity = em.find(Car.class, carId);
            em.remove(entity);
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }

        return Response
                .noContent()
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(Car car) throws Exception {
        if (car.getId() != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity("Unable to create Car, id was already set.")
                    .build();
        }


        Person owner;
        if ((owner = car.getOwner()) != null && owner.getId() != null) {
            car.setOwner(personResource.get(owner.getId()));
        }

        try {
            em.persist(car);
            em.flush();
        } catch (ConstraintViolationException cve) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(cve.getMessage())
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
        return Response
                .created(new URI("car/" + car.getId().toString()))
                .build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{carId}")
    @Transactional
    public Response update(@PathParam("carId") Integer carId, Car car) throws Exception {
        try {
            Car entity = em.find(Car.class, carId);

            if (null == entity) {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Car with id of " + carId + " does not exist.")
                        .build();
            }

            Person owner;
            if ((owner = car.getOwner()) != null && owner.getId() != null) {
                car.setOwner(personResource.get(owner.getId()));
            }

            em.merge(car);

            return Response
                    .ok(car)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

}
