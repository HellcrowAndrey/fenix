package com.github.admins.controllers.impl;

import com.github.admins.payload.Product;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.mockserver.model.Header;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.JsonBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.github.admins.controllers.impl.CustomProductControllerMocks.*;

public class CustomProductTestBase {

    private final MockServerClient client;

    public CustomProductTestBase() {
        this.client = new MockServerClient("127.0.0.1", 2222);
    }

    public void create() {
        Product requestPayload = requestProductPayload();
        Product responseProduct = responseProductPayload();
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/categories/fetch/Phone"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(categoryResponsePayload()))
        );

        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.POST.name())
                        .withPath("/v1/edit")
                        .withBody(JsonBody.json(requestPayload)),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.CREATED.value())
                        .withHeader(Header.header(
                                HttpHeaders.CONTENT_TYPE,
                                MediaType.APPLICATION_JSON_VALUE
                        )).withBody(JsonBody.json(responseProduct))
        );
    }

    public void readById() {
        Product responseProduct = responseProductPayload();
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch/1"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(responseProduct))
        );
    }

    public void readAllUnpublish() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.GET.name())
                        .withPath("/v1/fetch/un-publish"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        ).withBody(JsonBody.json(PRODUCTS_UN_PUBLISH))
        );
    }

    public void update() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.PUT.name())
                        .withPath("/v1/edit"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.OK.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        )
        );
    }

    public void changeStatus() {
        this.client.when(
                HttpRequest.request()
                        .withMethod(RequestMethod.DELETE.name())
                        .withPath("/v1/edit/1/used"),
                Times.exactly(1)
        ).respond(
                HttpResponse.response()
                        .withStatusCode(HttpStatus.NO_CONTENT.value())
                        .withHeader(
                                Header.header(
                                        HttpHeaders.CONTENT_TYPE,
                                        MediaType.APPLICATION_JSON_VALUE
                                )
                        )
        );
    }

}
