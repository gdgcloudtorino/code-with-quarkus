package org.gdgcloudtorino.quickstart

import io.smallrye.mutiny.Uni
import jakarta.ws.rs.*
import org.bson.types.ObjectId
import org.jboss.resteasy.reactive.RestResponse
import org.jboss.resteasy.reactive.RestResponse.ResponseBuilder
import java.net.URI


@Path("/fruits")
class FruitResource(private val fruitRepository: FruitRepository) {

    @GET
    fun list() = fruitRepository.listAll()

    @GET
    @Path("/{id}")
    fun get(id:String): Uni<Fruit?> {
        return fruitRepository.findById(ObjectId(id))
    }

    @POST
    fun create(fruit: Fruit): Uni<RestResponse<Fruit>> {
        return fruitRepository.persist(fruit)
            .map {
                ResponseBuilder
                    .created<Fruit>(URI.create("/fruits/${it.id.toString()}"))
                    .entity(fruit)
                    .build()
            }
    }

    @PUT
    @Path("/{id}")
    fun update(id:String,fruit: Fruit): Uni<Fruit> {
        fruit.id = ObjectId(id)
        return fruitRepository.update(fruit)
    }

    @DELETE
    @Path("/{id}")
    fun delete(id: String): Uni<Boolean> {
        return fruitRepository.deleteById(ObjectId(id))
    }

    @GET
    @Path("/search/{name}")
    fun search(name: String): Uni<List<Fruit>> {
        return fruitRepository.findByName(name).list()
    }

    @GET
    @Path("/count")
    fun count(): Uni<Long> {
        return fruitRepository.count()
    }
}