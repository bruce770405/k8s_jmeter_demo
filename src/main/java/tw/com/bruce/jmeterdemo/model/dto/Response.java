package tw.com.bruce.jmeterdemo.model.dto;

public class Response<T> {
    private T data;

    public Response() {
    }

    public Response(T response) {
        this.data = (T) response;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
