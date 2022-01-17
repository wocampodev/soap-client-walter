package dev.wocampo.soap.client;

import java.io.Serializable;

import dev.wocampo.soap.server.webservice.*;

import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;

public class WsProductsClient implements Serializable {

    private static final long serialVersionUID = -4184457396269278149L;

    private final WebServiceMessageCallback callback;
    private final transient WebServiceTemplate template;
    private final String uri;
    private final ObjectFactory factory;

    public WsProductsClient(WebServiceTemplate template, String uri, WebServiceMessageCallback callback) {
        this.template = template;
        this.callback = callback;
        this.uri = uri;
        factory = new ObjectFactory();
    }

    public GetProductsResponse handleWrapperGetProducts() {
        GetProductsRequest request = factory.createGetProductsRequest();
        return ((GetProductsResponse) template.marshalSendAndReceive(uri, request, callback));
    }

    public GetProductByIdResponse handleWrapperGetProductById(Integer id) {
        GetProductByIdRequest request = factory.createGetProductByIdRequest();
        request.setProductId(id);
        return ((GetProductByIdResponse) template.marshalSendAndReceive(uri, request, callback));
    }

    public SaveProductResponse handleWrapperSaveProduct(Product productFromClient) {
        SaveProductRequest request = factory.createSaveProductRequest();
        request.setName(productFromClient.getName());
        request.setPrice(productFromClient.getPrice());
        request.setDescription(productFromClient.getDescription());
        return ((SaveProductResponse) template.marshalSendAndReceive(uri, request, callback));
    }

    public UpdateProductResponse handleWrapperUpdateProduct(Integer id, Product productFromClient) {
        UpdateProductRequest request = factory.createUpdateProductRequest();
        Product product = new Product();
        product.setProductId(id);
        product.setName(productFromClient.getName());
        product.setPrice(productFromClient.getPrice());
        product.setDescription(productFromClient.getDescription());
        request.setProduct(product);
        return ((UpdateProductResponse) template.marshalSendAndReceive(uri, request, callback));
    }

    public DeleteProductResponse handleWrapperDeleteProduct(Integer id) {
        DeleteProductRequest request = factory.createDeleteProductRequest();
        request.setProductId(id);
        return ((DeleteProductResponse) template.marshalSendAndReceive(uri, request, callback));
    }

}
