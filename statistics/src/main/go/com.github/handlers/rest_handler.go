package handlers

import (
	"../config"
	"github.com/gorilla/mux"
	httpSwagger "github.com/swaggo/http-swagger"
	"log"
	"net/http"
)

type RestHandler struct {
	purchaseHandler *PurchasesHandler
	loginHandler    *LoginHandler
	viewsHandler    *ViewsHandler
	config          *config.Config
}

func NewRestHandler(
	purchaseHandler *PurchasesHandler, loginHandler *LoginHandler,
	viewsHandler *ViewsHandler, config *config.Config) *RestHandler {
	return &RestHandler{
		purchaseHandler: purchaseHandler,
		loginHandler:    loginHandler,
		viewsHandler:    viewsHandler,
		config:          config}
}

func (handler *RestHandler) Handler() http.Handler {
	router := mux.NewRouter()
	router.
		HandleFunc("/v1/purchases/{accountId}", handler.purchaseHandler.GetByAccountId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/purchases", handler.purchaseHandler.CreatePurchase).
		Methods(http.MethodPost)
	router.
		HandleFunc("/v1/logins/fetch/{accountId}", handler.loginHandler.GetByAccountId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/logins/edit", handler.loginHandler.CreateLogin).
		Methods(http.MethodPost)
	router.
		HandleFunc("/v1/views/{accountId}", handler.viewsHandler.GetByAccountId).
		Methods(http.MethodGet)
	router.
		HandleFunc("/v1/views", handler.viewsHandler.CreateViews).
		Methods(http.MethodPost)
	if handler.config.IsSwaggerEnable {
		router.PathPrefix("/swagger").Handler(httpSwagger.WrapHandler)
	}
	router.Use(loggingMiddleware)
	http.Handle("/", router)
	return router
}

func loggingMiddleware(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		log.Println(
			http.TimeFormat,
			"Method:", r.Method,
			"URL:", r.RequestURI)
		next.ServeHTTP(w, r)
	})
}