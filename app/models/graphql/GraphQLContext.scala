package models.graphql

import java.util.UUID

import util.Application

case class GraphQLContext(app: Application, user: UUID)
