package handlers

import (
	"github.com/gorilla/handlers"
	"github.com/gorilla/mux"
	"github.com/prometheus/client_golang/prometheus/promhttp"
	httpSwagger "github.com/swaggo/http-swagger"
	"net/http"
	"statistics/src/main/go/com.github/config"
)

type RestHandler struct {
	loginHandler          *LoginHandler
	viewsHandler          *ViewsHandler
	popularProductHandler *PopularProductHandler
	config                *config.Config
	tracer                *Tracer
}

func NewRestHandler(
	loginHandler *LoginHandler,
	viewsHandler *ViewsHandler,
	popularProductHandler *PopularProductHandler,
	config *config.Config,
	tracer *Tracer) *RestHandler {
	return &RestHandler{
		loginHandler:          loginHandler,
		viewsHandler:          viewsHandler,
		popularProductHandler: popularProductHandler,
		config:                config,
		tracer:                tracer}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1/logins/fetch/{userId}", handler.loginHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/fetch/time", handler.loginHandler.FindBetweenTime).
		Queries("start", "{start}", "end", "{end}").
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/edit", handler.loginHandler.CreateLogin).
		Methods(http.MethodPost)

	router.
		HandleFunc("/v1/views/fetch/{userId}", handler.viewsHandler.FindByUserId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views/fetch/time", handler.viewsHandler.FindBetweenTime).
		Queries("start", "{start}", "end", "{end}").
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views", handler.viewsHandler.FindViews).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views", handler.viewsHandler.CreateViews).
		Methods(http.MethodPost)

	router.
		HandleFunc("/v1/popular/view/{productId}", handler.popularProductHandler.UpdatePercentView).
		Methods(http.MethodPut)
	router.
		HandleFunc("/v1/popular/bought", handler.popularProductHandler.UpdatePercentBought).
		Methods(http.MethodPut)

	router.Handle("/metrics", promhttp.Handler())
	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	if handler.config.ZipkinEnable {
		zipkinMiddleware := handler.tracer.CreateMiddleware()
		router.Use(zipkinMiddleware)
	}
	cors := handlers.CORS(
		handlers.AllowedHeaders([]string{"content-type"}),
		handlers.AllowedOrigins([]string{"*"}),
		handlers.AllowCredentials(),
	)
	router.Use(cors)
	router.Use(prometheusMiddleware)
	http.Handle("/", router)
	return router
}
