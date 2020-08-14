package handlers

import (
	"../controllers"
	"../dto"
	log "../logger"
	"encoding/json"
	"github.com/gorilla/mux"
	"net/http"
	"strconv"
)

type LoginHandler struct {
	controller *controllers.LoginsController
}

func NewLoginHandler(controller *controllers.LoginsController) *LoginHandler {
	return &LoginHandler{controller: controller}
}

func (handler *LoginHandler) FindByUserId(w http.ResponseWriter, r *http.Request) {
	vars := mux.Vars(r)
	result := vars["userId"]
	accountId, err := strconv.ParseUint(result, 10, 64)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	login, err := handler.controller.FindByUserId(uint(accountId))
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: read all account information by account id.")
	ResponseSender(w, login, http.StatusOK)
}

func (handler *LoginHandler) FindBetweenTime(w http.ResponseWriter, r *http.Request) {
	start := r.FormValue("start")
	end := r.FormValue("end")
	if start == "" && end == "" {
		http.Error(w, "Required params is empty!", http.StatusBadRequest)
		return
	}
	login, err := handler.controller.FindBetweenTime(start, end)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: read all account information between", start, end)
	ResponseSender(w, login, http.StatusOK)
}

func (handler *LoginHandler) CreateLogin(w http.ResponseWriter, r *http.Request) {
	var payload dto.LoginDto
	err := json.NewDecoder(r.Body).Decode(&payload)
	login, err := handler.controller.CreateLogin(&payload)
	if err != nil {
		http.Error(w, err.Error(), http.StatusBadRequest)
		return
	}
	log.Debug("Enter: create new  user login information")
	ResponseSender(w, login, http.StatusCreated)
}
