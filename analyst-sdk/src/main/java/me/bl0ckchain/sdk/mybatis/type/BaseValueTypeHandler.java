package me.bl0ckchain.sdk.mybatis.type;

import me.bl0ckchain.sdk.mybatis.entity.ValueEnum;
import me.bl0ckchain.sdk.utils.EnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:supengcai@gmail.com">suppe</a>
 * @date 22/05/2018
 */
public class BaseValueTypeHandler<E extends Enum<?> & ValueEnum> extends BaseTypeHandler<ValueEnum> {

    /**
     * Enum class type.
     */
    private Class<E> type;

    public BaseValueTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        } else {
            this.type = type;
        }
    }

    /**
     *
     *
     * @param preparedStatement
     * @param i
     * @param valueEnum
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, ValueEnum valueEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, valueEnum.getValue());
    }

    /**
     *
     *
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public ValueEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int value = resultSet.getInt(s);
        return resultSet.wasNull() ? null : valueOf(value);
    }

    /**
     *
     *
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public ValueEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int value = resultSet.getInt(i);
        return resultSet.wasNull() ? null : valueOf(value);
    }

    /**
     *
     *
     * @param callableStatement
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public ValueEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int value = callableStatement.getInt(i);
        return callableStatement.wasNull() ? null : valueOf(value);
    }

    /**
     *
     *
     * @param code
     * @return
     */
    private E valueOf(int code) {
        try {
            return EnumUtils.valueOf(type, code);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + code + " to " + type.getSimpleName() + " by code value.", ex);
        }
    }
}
