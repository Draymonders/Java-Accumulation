package cn.draymonder.entity.dto;

/**
 * @Description:
 * @Date 2020/08/18 22:40
 * @auther Draymonder
 */
public class Result<T> {

  int code;
  String msg;
  T data;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
