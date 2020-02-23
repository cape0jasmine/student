package util;

import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.lang.reflect.Type;  
import java.sql.Connection;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.sql.Statement;  
import java.sql.Types;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;   
public class DBUtil {  
    /** 
     * 对操作的数据库回滚 
     * @param con 对数据库操作所得到的链接 
     */  
    public static void rollBack(Connection con){  
        try {  
            con.rollback();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
    /*** 
     *  
     * @param con 数据库jdbc链接 
     * @param sql 执行的sql语句 
     * @return 返回查询的记录数，记录存储在集合List里面， 
     * 里面的元素是集合Map,key是数据库中的字段类型，value是 
     * 字段类型对应的值 
     * @throws SQLException 
     */  
    public static List<Map<String, Object>> executeQuery(Connection con, String sql) throws SQLException{  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
        try {  
            pst = con.prepareStatement(sql);  
            rs = pst.executeQuery();  
            return getListFromRsLowerCase(rs);  
        }finally{  
            closeRs(rs);  
            closePst(pst);  
        }  
    }  
    /*** 
     *  执行sql语句,把结果集存放到List集合里，集合的元素是dao对象 
     * @param con 数据库得到的链接 
     * @param sql 执行查询的sql语句 
     * @param c   把一条条记录要映射的dao类中的对象中去 
     * @return 
     * @throws SQLException 
     */  
    public static List<Object> executeQuery(Connection con, String sql, Class<?> c) throws SQLException{  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
        try {  
            pst = con.prepareStatement(sql);  
            rs = pst.executeQuery();  
            return getListFromRs(rs, c);  
        }finally{  
            closeRs(rs);  
            closePst(pst);  
        }  
    }  
    /** 
     * 得到结果集存储到list中 
     * @param rs 查询的结果集 
     * @return  
     * @throws SQLException 
     */  
    public static List<Map<String, Object>> getListFromRs(ResultSet rs) throws SQLException{  
        ResultSetMetaData md = rs.getMetaData();//得到结果集列的属性  
        int columns = md.getColumnCount();//得到记录有多少列  
        int i;  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        while(rs.next()){  
            Map<String, Object> map = new HashMap<String, Object>();  
            for(i = 0; i < columns; i++){  
                map.put(md.getColumnName(i + 1), getValueByType(rs, md.getColumnType(i + 1), md.getColumnName(i + 1)));  
            }  
            list.add(map);  
        }  
        return list;  
    }  
    /** 
     * 这个与getListFromRs(ResultSet rs)差不多，只是把数据库的字段变成小写 
     *  
     * @param rs 
     * @return 
     * @throws SQLException 
     */  
    public static List<Map<String, Object>> getListFromRsLowerCase(ResultSet rs) throws SQLException{  
        ResultSetMetaData md = rs.getMetaData();  
        int columns = md.getColumnCount();  
        int i;  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        while(rs.next()){  
            Map<String, Object> map = new HashMap<String, Object>();  
            for(i = 0; i < columns; i++){  
                map.put(md.getColumnName(i + 1).toLowerCase(), getValueByType(rs, md.getColumnType(i + 1), md.getColumnName(i + 1)));  
            }  
            list.add(map);  
        }  
        return list;  
    }  
    /** 
     * 这个与getListFromRs(ResultSet rs)功能一样，只是把数据库的字段变成大写 
     * @param rs 
     * @return 
     * @throws SQLException 
     */  
    public static List<Map<String, Object>> getListFromRsUpperCase(ResultSet rs) throws SQLException{  
        ResultSetMetaData md = rs.getMetaData();  
        int columns = md.getColumnCount();  
        int i;  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        while(rs.next()){  
            Map<String, Object> map = new HashMap<String, Object>();  
            for(i = 0; i < columns; i++){  
                map.put(md.getColumnName(i + 1).toUpperCase(), getValueByType(rs, md.getColumnType(i + 1), md.getColumnName(i + 1)));  
            }  
            list.add(map);  
        }  
        return list;  
    }  
    /*** 
     *  
     * @param rs 查询的结果集 
     * @param c  集合元素存放的dao对象 
     * @return 
     * @throws SQLException 
     */  
    public static List<Object> getListFromRs(ResultSet rs, Class<?> c) throws SQLException{  
        List<Object> list = new ArrayList<Object>();  
        try {  
            while(rs.next()){  
                Object o = initObjectFromRsIfExist(rs, c);  
                list.add(o);  
            }  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        }  
        return list;  
    }  
    /** 
     *  
     * @param rs 查询的结果集 
     * @param c 结果集一条记录，而一条记录所对应的dao类 
     * @return 
     * @throws SQLException 
     */  
    public static Object getFirstObjectFromRs(ResultSet rs, Class<?> c) throws SQLException{  
        Object o = null;  
        try {  
            o = initObjectFromRsIfExist(rs, c);  
        } catch (InstantiationException e) {  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
        return o;  
    }  
    /*** 
     *  
     * @param rs 查询出来的结果集 
     * @param type SQL type from java.sql.Types 
     * @param name 数据库记录所对应的字段名称 
     * @return 返回一条记录的一个列值 
     * @throws SQLException 
     */  
    private static Object getValueByType(ResultSet rs, int type, String name) throws SQLException{  
        switch(type){  
            case Types.NUMERIC:  
                    return rs.getLong(name);                  
            case Types.VARCHAR:  
                //if(rs.getString(name)==null){  
                    //return "";  
                //}  
                return rs.getString(name);  
            case Types.DATE:  
                //if(rs.getDate(name)==null){  
                    //return System.currentTimeMillis();  
            //  }  
                return rs.getDate(name);  
            case Types.TIMESTAMP:  
                return rs.getTimestamp(name).toString().substring(0,rs.getTimestamp(name).toString().length()-2);  
            case Types.INTEGER:  
                return rs.getInt(name);  
            case Types.DOUBLE:  
                return rs.getDouble(name);  
            case Types.FLOAT:  
                return rs.getFloat(name);  
            case Types.BIGINT:  
                return rs.getLong(name);  
            default:  
                return rs.getObject(name);  
        }  
    }  
    /*** 
     * 查询dao映射的字段是否在记录在数据库包含的字段 
     * @param rs 查询的记录集 
     * @param fieldName dao映射的字段 
     * @return 如果包含在数据库记录集里面，返回true，否则false 
     * @throws SQLException 
     */  
    private static boolean rsContainsFields(ResultSet rs, String fieldName) throws SQLException{  
        ResultSetMetaData md = rs.getMetaData();  
        for(int i = 0; i < md.getColumnCount(); i++){  
            if(md.getColumnName(i + 1).equalsIgnoreCase(fieldName)){  
                return true;  
            }  
        }  
        return false;  
    }  
    /*** 
     * 这个函数与initObjectFromRsIfExist函数实现的功能是一样，只是 
     * 没有判断dao中的字段是否与数据库记录所定义的字段是一样的, 
     * 没有判断时如果自己设置的dao字段与数据库的字段不一致就会报异常 
     * @param rs 
     * @param c 
     * @return 
     * @throws InstantiationException 
     * @throws SQLException 
     * @throws IllegalAccessException 
     */  
    private static Object initObjectFromRs(ResultSet rs, Class<?> c) throws InstantiationException, SQLException, IllegalAccessException{  
        Object o = c.newInstance();  
        Method[] methods = o.getClass().getMethods();  
        for(Method m: methods){  
            if(m.getName().startsWith("set")){  
                try {                     
                    m.invoke(o, getParamValueFromRs(rs, m));                      
                } catch (IllegalArgumentException e) {  
                    throw new RuntimeException("IllegalArgumentException:" + e + "\nMethods:" + m.getName());  
                } catch (InvocationTargetException e) {  
                    throw new RuntimeException("InvocationTargetException:" + e + "\nMethods:" + m.getName());  
                }  
            }  
        }  
        return o;  
    }  
    /*** 
     *  
     * 把数据库的一条记录映射到相应的dao对象中， 
     * 如果dao中的字段与数据库字段不一致，返回的就是dao数据类型定义的默认值 
     * 如：dao的字段long vehicleID;而数据库的字段是vehicle_id，那么返回的 
     * 就定义的默认值0. 
     * @param rs 查询的结果集 
     * @param c 结果集一条记录，而一条记录所对应的dao类 
     * @return 
     * @throws SQLException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */  
    private static Object initObjectFromRsIfExist(ResultSet rs, Class<?> c) throws SQLException, IllegalAccessException, InstantiationException{  
        Object o = c.newInstance();//一条记录的dao，新建对象  
        Method[] methods = o.getClass().getMethods();//dao对象所有的方法  
        String field;  
        for(Method m: methods){  
            //得到dao字段，如getRegdate,转换成Regdate  
            field = m.getName().substring(3);  
            //查询dao映射的字段是否在记录在数据库包含的字段,dao方法对set开头的方法进行处理  
            //因为要将结果集映射到dao里面  
            if(m.getName().startsWith("set") && rsContainsFields(rs, field)){  
                try {                     
                    m.invoke(o, getParamValueFromRs(rs, m));                      
                } catch (IllegalArgumentException e) {  
                    throw new RuntimeException("IllegalArgumentException:" + e + "\nMethods:" + m.getName());  
                } catch (InvocationTargetException e) {  
                    throw new RuntimeException("InvocationTargetException:" + e + "\nMethods:" + m.getName());  
                }  
            }  
        }  
        return o;  
    }  
    /*** 
     *  
     * @param rs 查询的结果集 
     * @param m  dao映射字段对应的一个set方法 
     * @return 
     * @throws SQLException 
     */  
    private static Object getParamValueFromRs(ResultSet rs, Method m) throws SQLException  
    {  
        String fieldName = m.getName().substring(3);  
        Type type = m.getGenericParameterTypes()[0];//获取set方法参数的类型        
        return getValueFromRs(rs, fieldName, type);  
    }  
    /** 
     * 获取数据库一条记录的一个列值 
     * @param rs 查询的结果集 
     * @param fieldName dao数据字段，也就是数据库记录的数据字段类型 
     * @param t 参数的数据类型 
     * @return 
     * @throws SQLException 
     */  
    private static Object getValueFromRs(ResultSet rs, String fieldName, Type t) throws SQLException{  
        String type = t.toString();  
        try{  
            if(type.equals("int") || type.equals("class java.lang.Integer")){  
                return rs.getInt(fieldName);  
            }else if(type.equals("float") || type.equals("class java.lang.Float")){  
                return rs.getFloat(fieldName);  
            }else if(type.equals("double") || type.equals("class java.lang.Double")){  
                return rs.getDouble(fieldName);  
            }else if(type.equals("long") || type.equals("class java.lang.Long")){  
                return rs.getLong(fieldName);  
            }else if(type.equals("class java.lang.String")){  
                return rs.getString(fieldName);  
            }else if(type.equals("class java.sql.Timestamp")){  
                return rs.getTimestamp(fieldName);  
            }else if(type.equals("class java.sql.Date")){  
                return rs.getDate(fieldName);  
            }else if(type.equals("class java.sql.Time")){  
                return rs.getTime(fieldName);  
            }  
        }catch(SQLException e){  
            throw new SQLException("SQLException when get field:" + fieldName + "\n" + e);  
        }  
        throw new RuntimeException("getValueFromRsByField fail, field type is:" + type + ",field name is:" + fieldName);  
    }  
    /*** 
     * 关闭数据库多个结果集 
     * @param rss 
     */  
    public static void closeRs(ResultSet... rss){  
        for(ResultSet rs: rss){  
            if(rs != null){  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                }  
            }  
        }  
    }  
    /** 
     * 关闭数据库多个psts 
     * @param psts 
     */  
    public static void closePst(Statement... psts){  
        for(Statement pst: psts){  
            if(pst != null){  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                }  
            }  
        }  
    }  
    /** 
     * 关闭数据库所得到的多个链接 
     * @param cons 
     */  
    public static void closeCon(Connection... cons){  
        for(Connection con: cons){  
            if(con != null)  
            {  
                try {  
                    con.close();  
                } catch (SQLException e) {  
                }  
            }  
        }  
    }  
}  
