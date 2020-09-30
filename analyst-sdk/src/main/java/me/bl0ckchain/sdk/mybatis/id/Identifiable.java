package me.bl0ckchain.sdk.mybatis.id;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">supeng cai</a>
 * @version V1.0, 16/2/22
 */
public interface Identifiable<ID> {

    /***
     * get id
     *
     * @return
     */
    ID getId();


    /***
     * set id
     *
     * @param id
     */
    void setId(ID id);
}
