package org.gdgcloudtorino.quickstart

import io.quarkus.mongodb.panache.kotlin.reactive.ReactivePanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import org.bson.Document

@ApplicationScoped
class FruitRepository : ReactivePanacheMongoRepository<Fruit> {

    fun findByName(name:String) = find(Document("name",Document("\$regex",name)
            .append("\$options","i")))

}