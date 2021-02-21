package basement.foundation.exceptiontest;

/**
 * @program: leetcode_forwork
 * @Date: 2021/2/13 10:21
 * @Author: Mr.Yang
 * @Description:
 */
public class CustomerException extends RuntimeException{
    private String retCd;
    private String msgDes;

    // 重载构造器
    public CustomerException(){
        super();
    }

    public CustomerException(String message) {
        super(message);
        this.msgDes = message;
    }

    public CustomerException(String retCd, String msgDes){
        super();
        this.retCd = retCd;
        this.msgDes = msgDes;
    }

    public String getRetCd(){
        return this.retCd;
    }

    public String getMsgDes() {
        return msgDes;
    }
}
