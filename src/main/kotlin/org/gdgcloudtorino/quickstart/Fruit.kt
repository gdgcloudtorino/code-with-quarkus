package org.gdgcloudtorino.quickstart

import io.quarkus.mongodb.panache.common.MongoEntity
import io.quarkus.runtime.annotations.RegisterForReflection
import org.bson.types.ObjectId

@RegisterForReflection
@MongoEntity(collection = "fruits")
class Fruit {
    var id: ObjectId? = null
    lateinit var name:String
    lateinit var description:String
}