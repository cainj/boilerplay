package models.graphql

import models.sandbox.SandboxSchema
import sangria.execution.deferred.DeferredResolver
import sangria.schema._

object Schema {
  val resolver: DeferredResolver[GraphQLContext] = DeferredResolver.fetchers()

  val queryFields = SandboxSchema.queryFields // ++ others

  val queryType = ObjectType(
    name = "Query",
    description = "The main query interface.",
    fields = queryFields
  )

  val mutationFields = SandboxSchema.mutationFields // ++ others

  val mutationType = ObjectType(
    name = "Mutation",
    description = "The main mutation interface.",
    fields = mutationFields
  )

  val schema = sangria.schema.Schema(
    query = queryType,
    mutation = Some(mutationType)
  )
}
