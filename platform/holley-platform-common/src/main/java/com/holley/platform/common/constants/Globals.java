package com.holley.platform.common.constants;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Globals {

    public static final String              ADMIN                = "admin";                                 // 系统管理员账号
    public static final int                 ADMIN_ROLE_ID        = 1;                                       // 系统管理员角色
    public static final String              INITPASSWORD         = "hello123";                              // 初始化密码

    public static final String              ALL                  = "all";                                   // 所有

    public static final String              ROWSPLIT             = "@";                                     // 分隔符
    public static final String              COLUMNSPLIT          = "#";                                     // 分隔符

    public static int                       LOGIN_FAIL_TOTAL     = 5;                                       // 总共登录失败次数
    public static int                       VALID_FAIL_TOTAL     = 5;                                       // 金额验证次数
    public static int                       LOGIN_INTERVAL       = 30;                                      // 短时间登录失败超多次后再次允许登录的时间间隔

    public static final String              SUPER_PASSWORD       = "buzhidao";                              // 超级密码前缀

    public static final String              WEB_ROOT             = "webRoot";                               // 根目录
    public static final String              IMG_URL              = "imgUrl";                                // 图片存放地址前缀

    public static final String              DEFAULT_MESSAGE      = "success";                               // 默认结果
    public static final String              PARAM_ERROR_MESSAGE  = "参数非法";                                  // 参数非法提示文字
    public static final String              MSG                  = "msg";                                   // 提示参数
    public static final String              PAGE                 = "page";
    public static final String              NULL_MESSAGE         = "--";                                    // 空值时默认显示的信息

    public static final String              EXPORT_TYPE          = "exportType";
    public static final String              EXPORT_TEXT          = "text";                                  // 导出全部为文本
    public static final String              EXPORT_DEFAULT       = "default";                               // 默认导出
    public static boolean                   ISVALIDATEEXCELCOLOR = false;                                   // 是否启用导出excel当字段为空时显示红色
    public static final int                 EXPORT_MAX           = 5000;                                    // 导出Excel最多记录数

    public static final int                 REQUEST_TYPE_ADD     = 1;                                       // 新增
    public static final int                 REQUEST_TYPE_EDIT    = 2;                                       // 修改
    public static final int                 REQUEST_TYPE_DETAIL  = 3;                                       // 详细

    public static final Map<String, Object> THREAD_INFO          = new ConcurrentHashMap<String, Object>(); // 线程运行信息

    public static final Short               TASK_AUTO            = 1;                                       // 计算类型
    public static final Short               TASK_MANUAL          = 2;

    public static int                       MAX_LEN_RECV         = 102400;
}
