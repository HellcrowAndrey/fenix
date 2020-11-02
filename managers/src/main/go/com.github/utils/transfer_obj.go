package utils

import (
	"managers/src/main/go/com.github/dto"
	"managers/src/main/go/com.github/entity"
)

func ToPurchase(managerId string, data *dto.PurchaseDto) *entity.Purchase {
	return &entity.Purchase{ManagerId: managerId, OrderId: data.OrderId, Status: data.Status}
}

func FromManager(manager *entity.Manager) *dto.ManagerDto {
	return &dto.ManagerDto{
		FirstName: manager.FirstName,
		LastName:  manager.LastName,
	}
}
