// GENERATED BY THE COMMAND ABOVE; DO NOT EDIT
// This file was generated by swaggo/swag

package docs

import (
	"bytes"
	"encoding/json"
	"strings"

	"github.com/alecthomas/template"
	"github.com/swaggo/swag"
)

var doc = `{
    "schemes": {{ marshal .Schemes }},
    "swagger": "2.0",
    "info": {
        "description": "{{.Description}}",
        "title": "{{.Title}}",
        "termsOfService": "http://swagger.io/terms/",
        "contact": {},
        "license": {
            "name": "Apache 2.0",
            "url": "http://www.apache.org/licenses/LICENSE-2.0.html"
        },
        "version": "{{.Version}}"
    },
    "host": "{{.Host}}",
    "basePath": "{{.BasePath}}",
    "paths": {
        "/v1/logins/edit": {
            "post": {
                "description": "Create a new login",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "logins"
                ],
                "summary": "Create a new login",
                "parameters": [
                    {
                        "description": "Create login",
                        "name": "account",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "$ref": "#/definitions/dto.LoginDto"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/dto.LoginDto"
                        }
                    }
                }
            }
        },
        "/v1/logins/fetch": {
            "get": {
                "description": "Get details of user logins",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "logins"
                ],
                "summary": "Get details of user logins",
                "parameters": [
                    {
                        "type": "string",
                        "description": "name search by start",
                        "name": "start",
                        "in": "query"
                    },
                    {
                        "type": "string",
                        "description": "name search by end",
                        "name": "end",
                        "in": "query"
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/dto.LoginDto"
                        }
                    }
                }
            }
        },
        "/v1/logins/fetch/{userId}": {
            "get": {
                "description": "Get details of user logins",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "logins"
                ],
                "summary": "Get details of user logins",
                "parameters": [
                    {
                        "type": "integer",
                        "description": "User ID",
                        "name": "userId",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/dto.LoginDto"
                        }
                    }
                }
            }
        },
        "/v1/views": {
            "get": {
                "description": "Get details of user views",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "views"
                ],
                "summary": "Get details of user views",
                "parameters": [
                    {
                        "type": "string",
                        "description": "Bearer",
                        "name": "Authorization",
                        "in": "header",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/dto.ProductDto"
                        }
                    }
                }
            },
            "post": {
                "description": "Create a new views",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "views"
                ],
                "summary": "Create a new views",
                "parameters": [
                    {
                        "description": "Create view",
                        "name": "view",
                        "in": "body",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    },
                    {
                        "type": "string",
                        "description": "Bearer",
                        "name": "Authorization",
                        "in": "header",
                        "required": true
                    }
                ],
                "responses": {
                    "201": {}
                }
            }
        },
        "/v1/views/fetch": {
            "get": {
                "description": "Get details of user views",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "views"
                ],
                "summary": "Get details of user views",
                "parameters": [
                    {
                        "type": "string",
                        "description": "name search by start",
                        "name": "start",
                        "in": "query"
                    },
                    {
                        "type": "string",
                        "description": "name search by end",
                        "name": "end",
                        "in": "query"
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/dto.ProductDto"
                        }
                    }
                }
            }
        },
        "/v1/views/fetch/{userId}": {
            "get": {
                "description": "Get details of user views",
                "consumes": [
                    "application/json"
                ],
                "produces": [
                    "application/json"
                ],
                "tags": [
                    "views"
                ],
                "summary": "Get details of user views",
                "parameters": [
                    {
                        "type": "integer",
                        "description": "User ID",
                        "name": "userId",
                        "in": "path",
                        "required": true
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "schema": {
                            "$ref": "#/definitions/dto.ProductDto"
                        }
                    }
                }
            }
        }
    },
    "definitions": {
        "dto.CommentDto": {
            "type": "object",
            "properties": {
                "description": {
                    "type": "string",
                    "example": "This is comment description"
                },
                "id": {
                    "type": "integer",
                    "example": 1
                },
                "name": {
                    "type": "string",
                    "example": "comment author"
                }
            }
        },
        "dto.LoginDto": {
            "type": "object",
            "properties": {
                "createAt": {
                    "type": "string",
                    "example": "2019-11-09T21:21:46+00:00"
                },
                "device": {
                    "type": "string",
                    "example": "phone"
                },
                "id": {
                    "type": "integer",
                    "example": 1
                },
                "location": {
                    "type": "string",
                    "example": "192.168.1.1"
                },
                "userId": {
                    "type": "integer",
                    "example": 12
                }
            }
        },
        "dto.ProductDto": {
            "type": "object",
            "properties": {
                "category": {
                    "type": "string",
                    "example": "phone"
                },
                "comments": {
                    "type": "array",
                    "items": {
                        "$ref": "#/definitions/dto.CommentDto"
                    }
                },
                "description": {
                    "type": "string",
                    "example": "This is phone"
                },
                "id": {
                    "type": "integer",
                    "example": 1
                },
                "images": {
                    "type": "array",
                    "items": {
                        "type": "string"
                    }
                },
                "name": {
                    "type": "string",
                    "example": "Phone"
                },
                "previewImage": {
                    "type": "string",
                    "example": "image"
                },
                "price": {
                    "type": "number",
                    "example": 1.11
                },
                "quantity": {
                    "type": "integer",
                    "example": 10
                },
                "specifications": {
                    "type": "array",
                    "items": {
                        "$ref": "#/definitions/dto.SpecificationDto"
                    }
                }
            }
        },
        "dto.SpecificationDto": {
            "type": "object",
            "properties": {
                "description": {
                    "type": "string",
                    "example": "This is specification description"
                },
                "id": {
                    "type": "integer",
                    "example": 1
                },
                "name": {
                    "type": "string",
                    "example": "specificationName"
                }
            }
        }
    }
}`

type swaggerInfo struct {
	Version     string
	Host        string
	BasePath    string
	Schemes     []string
	Title       string
	Description string
}

// SwaggerInfo holds exported Swagger Info so clients can modify it
var SwaggerInfo = swaggerInfo{
	Version:     "1.0",
	Host:        "",
	BasePath:    "/",
	Schemes:     []string{},
	Title:       "Statistics API",
	Description: "This is a sample service for managing statistics",
}

type s struct{}

func (s *s) ReadDoc() string {
	sInfo := SwaggerInfo
	sInfo.Description = strings.Replace(sInfo.Description, "\n", "\\n", -1)

	t, err := template.New("swagger_info").Funcs(template.FuncMap{
		"marshal": func(v interface{}) string {
			a, _ := json.Marshal(v)
			return string(a)
		},
	}).Parse(doc)
	if err != nil {
		return doc
	}

	var tpl bytes.Buffer
	if err := t.Execute(&tpl, sInfo); err != nil {
		return doc
	}

	return tpl.String()
}

func init() {
	swag.Register(swag.Name, &s{})
}
