package me.bl0ckchain.sdk.mybatis.id;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 2016/9/27
 */
public interface IdGenerator<ID> {

    /***
     * generate id
     *
     * @return
     */
    ID generateID();
}
