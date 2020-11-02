package services

import (
	"statistics/src/main/go/com.github/entity"
	"statistics/src/main/go/com.github/repository"
)

type LoginService struct {
	repo *repository.LoginRepo
}

func NewLoginService(repo *repository.LoginRepo) *LoginService {
	return &LoginService{repo: repo}
}

func (service *LoginService) ReadByUserId(userId string) ([]*entity.Login, error) {
	return service.repo.FindByUserId(userId)
}

func (service *LoginService) ReadBetweenTime(start string, end string) ([]*entity.Login, error) {
	return service.repo.FindBetweenTime(start, end)
}

func (service *LoginService) CreateLogin(login *entity.Login) (*entity.Login, error) {
	return service.repo.Save(login)
}
