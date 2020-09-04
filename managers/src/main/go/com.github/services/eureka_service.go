package services

import (
	"../config"
	"github.com/hudl/fargo"
	log "github.com/sirupsen/logrus"
	"strconv"
	"time"
)

type EurekaService struct {
	config     *config.Config
	connection *fargo.EurekaConnection
}

func NewEurekaService(config *config.Config, connection *fargo.EurekaConnection) *EurekaService {
	return &EurekaService{config: config, connection: connection}
}

func (service *EurekaService) Run() {
	instance := service.createInstance()
	err := service.connection.DeregisterInstance(&instance)
	err = service.connection.RegisterInstance(&instance)
	if err != nil {
		panic("Can not connect to eureka.")
	} else {
		HeartBeat(service.connection, &instance)
	}
}

func (service *EurekaService) createInstance() fargo.Instance {
	port, _ := strconv.Atoi(service.config.ServerPort)
	return fargo.Instance{
		InstanceId:     service.config.EurekaConfig.InstanceId,
		HostName:       service.config.EurekaConfig.HostName,
		Port:           port,
		SecurePort:     service.config.EurekaConfig.SecurePort,
		App:            service.config.EurekaConfig.App,
		IPAddr:         service.config.EurekaConfig.IPAddr,
		VipAddress:     service.config.EurekaConfig.VipAddress,
		HomePageUrl:    service.config.EurekaConfig.HomePageUrl,
		StatusPageUrl:  service.config.EurekaConfig.StatusPageUrl,
		HealthCheckUrl: service.config.EurekaConfig.HealthCheckUrl,
		DataCenterInfo: fargo.DataCenterInfo{Name: fargo.MyOwn},
		Status:         fargo.UP,
		LeaseInfo: fargo.LeaseInfo{
			DurationInSecs: 90,
		},
	}
}

func HeartBeat(ec *fargo.EurekaConnection, i *fargo.Instance) {
	ticker := time.Tick(time.Duration(30*1000) * time.Millisecond)
	for {
		select {
		case <-ticker:
			if err := ec.HeartBeatInstance(i); err != nil {
				log.Warn("Lost connection to eureka")
			}
		}
	}
}
