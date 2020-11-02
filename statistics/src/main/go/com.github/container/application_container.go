package container

import (
	"go.uber.org/dig"
	"statistics/src/main/go/com.github/config"
	"statistics/src/main/go/com.github/controllers"
	"statistics/src/main/go/com.github/handlers"
	"statistics/src/main/go/com.github/logger"
	"statistics/src/main/go/com.github/migration"
	"statistics/src/main/go/com.github/repository"
	"statistics/src/main/go/com.github/server"
	"statistics/src/main/go/com.github/services"
)

func BuildContainer() *dig.Container {
	container := dig.New()
	err := container.Provide(config.NewConfig)
	err = container.Provide(config.ConnectEureka)
	err = container.Provide(logger.NewLogger)
	err = container.Provide(config.ConnectDatabase)
	err = container.Provide(migration.NewDataBaseMigration)

	err = container.Provide(repository.NewViewRepo)
	err = container.Provide(repository.NewLoginRepo)
	err = container.Provide(repository.NewPopularProductRepo)

	err = container.Provide(services.NewLoginService)
	err = container.Provide(services.NewViewService)
	err = container.Provide(services.NewEurekaService)
	err = container.Provide(services.NewProductService)
	err = container.Provide(services.NewPopularProductService)

	err = container.Provide(controllers.NewLoginsController)
	err = container.Provide(controllers.NewViewsController)
	err = container.Provide(controllers.NewPopularProductController)

	err = container.Provide(handlers.NewRestHandler)
	err = container.Provide(handlers.NewLoginHandler)
	err = container.Provide(handlers.NewViewsHandler)
	err = container.Provide(handlers.NewTracer)
	err = container.Provide(handlers.NewPopularProductHandler)

	err = container.Provide(server.NewServer)

	if err != nil {
		panic(err)
	}
	return container
}
