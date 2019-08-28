package com.umpay;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Const {
	public static final String ENV = "env";// 线上、UAT、测试环境选择
	public static final String ENABLED = "enabled";// 用例是否加入自动化
	public static final String CASETYPE = "caseType";// 用例级别
	public static final String XMLFILENAMEKEY = "xmlFileName";// 运行suit的key
	public static final String XMLFILENAMEVALUE = "suitP0.xml";// 运行suit的value
	public static final String RENVIRONMENT = "RunEnvironment";// 运行哪一套环境ABCD
	public static final String RPID = "rpid";
	public static final String REQDATE = "reqDate";
	public static final String REQTIME = "reqTime";
    public static final String BANK_PAYURL = "bank_payurl";//pbg返回的支付链接
    public static final int MAX_RETRY_COUNT = 2;//重跑次数
    /** 移动端常量 */
    public static final String UID = "GSL0217302005195";//华为手机
    public static final String AUTH_CODE = "auth_code";//手机支付授权码
    public static final String SCANCODE_TYPE = "scancode_type";
    public static final String WXappPackage = "com.tencent.mm";//华为微信appPackage
    public static final String WXappActivity = "com.tencent.mm.ui.LauncherUI";//华为微信appActivity
    public static final String AliPayappPackage = "com.eg.android.AlipayGphone";//华为支付宝appPackage
    public static final String AliPayappActivity = "com.eg.android.AlipayGphone.AlipayLogin";//华为支付宝appActivity
    /** 百度云的注册账号 */
    public static final String APP_ID = "11318447";
	public static final String API_KEY = "G5qyNNjEeudeNohB8UboOHV7";
	public static final String SECRET_KEY = "0zZHgCoR8zztCLTUCjRLhrR25xDsEnaY";
	

	
	

	public static Map<String, Object> pkMap = new HashMap<String, Object>();
	public static final String UMPAYSTIE_SERVICE = "/pay/payservice.do";
	public static final String mer_id = "mer_id";
	public static final String PLAIN = "plain";
	public static final String WEIXIN = "weixin";
	public static final String ALIPAY = "alipay";
	

	
	/** DB中订单号RETCODE */
	public static final String RETCODE = "RETCODE";
	public static final String RETMSG = "retMsg";
	public static final String ORIG_RETMSG= "orig_retmsg";
	public static final String trans_type = "trans_type";
	public static final String TRANS_TYPE = "trans_type";
	public static final String PLAT_APP_NAME_PAY = "spay";
	public static final String METHOD_GET = "get";
	public static final String METHOD_POST = "post";
	public static final String SUCCESS = "0000";
	public static final String PAYSERVICE = "payservice";
	public static final String USERIP = "userIp";
	public static String PLAT_URL = "";
	public static String STATE_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";//订单状态为等待支付WAIT_BUYER_PAY

	/** DB中订单号MERID */
	public static final String MERID = "MERID";
	/** 接口名称 */
	public static final String SERVICE = "service";
	/** 签名方式 */
	public static final String SIGN_TYPE = "sign_type";
	/** 签名 */
	public static final String SIGN = "sign";
	/** 参数字符编码集 */
	public static final String CHARSET = "charset";
	/** 商户编号 */
	public static final String MER_ID = "mer_id";
	/** 响应数据格式 */
	public static final String RES_FORMAT = "res_format";
	/** 原商户订单日期 */
	public static final String MER_DATE = "mer_date";
	/** 原商户订单号 */
	public static final String ORDER_ID = "order_id";
	/** 原订单类型 */
	public static final String ORDER_TYPE = "order_type";
	/** U付订单号 */
	public static final String TRADE_NO = "trade_no";
	/** 商品号 */
	public static final String GOODS_ID = "goods_id";
	/** 商品描述信息 */
	public static final String GOODS_INF = "goods_inf";
	/** 媒介标识 */
	public static final String MEDIA_ID = "media_id";
	/** 媒介类型 */
	public static final String MEDIA_TYPE = "media_type";
	/** 服务器异步通知页面路径 */
	public static final String NOTIFY_URL = "notify_url";
	/** 返回页面 */
	public static final String RET_URL = "ret_url";
	/** 退款流水号 */
	public static final String REFUND_NO = "refund_no";
	/** 退款金额 */
	public static final String REFUND_AMOUNT = "refund_amount";
	/** 原订单金额 */
	public static final String ORG_AMOUNT = "org_amount";
	/** 分账退款数据 */
	public static final String SPLIT_REFUND_LIST = "split_refund_list";
	/** 子商户号 */
	public static final String SUB_MER_ID = "sub_mer_id";
	/** 子订单号 */
	public static final String SUB_ORDER_ID = "sub_order_id";
	/** 退款描述信息 */
	public static final String REFUND_DESC = "refund_desc";
	/** 退费状态 */
	public static final String REFUND_STATE = "refund_state";
	/** 退费金额 */
	public static final String REFUND_AMT = "refund_amt";
	/** 分账退费结果 */
	public static final String SPLIT_REFUND_RESULT = "split_refund_result";
	/** 交易错误码 */
	public static final String ERROR_CODE = "error_code";
	/** 子订单已退费金额 */
	public static final String SUB_REFUND_AMT = "sub_refund_amt";
	/** 是否成功 */
	public static final String IS_SUCCESS = "is_success";
	/** 对账日期 */
	public static final String SETTLE_DATE = "settle_date";
	/** 对账类型 */
	public static final String SETTLE_TYPE = "settle_type";
	/** 付款币种 */
	public static final String AMT_TYPE = "amt_type";
	/** 金额 */
	public static final String AMOUNT = "amount";

	/** 账户名称 */
	public static final String ACCOUNT_NAME = "account_name";

	/** 账户名称 */
	public static final String CUST_NAME = "cust_name";

	/** 账户名称 */
	public static final String MER_CUST_NAME = "mer_cust_name";
	/** 商户用户标识 */
	public static final String MER_CUST_ID = "mer_cust_id";

	/** 默认支付方式 */
	public static final String PAY_TYPE = "pay_type";
	/** 默认银行 */
	public static final String GATE_ID = "gate_id";
	/** 借记卡支付类别 */
	public static final String DEBIT_PAY_TYPE = "debit_pay_type";
	/** 交易密码 */
	public static final String PASS_WD = "pass_wd";
	/** 证件类型 */
	public static final String IDENTITY_TYPE = "identity_type";
	/** 商户私有域 */
	public static final String MER_PRIV = "mer_priv";
	/** 订单过期时常 */
	public static final String EXPIRE_TIME = "expire_time";
	/** 卡号 */
	public static final String CARD_ID = "card_id";
	/** 用户业务协议号 */
	public static final String USER_BUSI_AGREEMENT_ID = "usr_busi_agreement_id";
	/** 支付协议号 */
	public static final String USER_PAY_AGREEMENT_ID = "usr_pay_agreement_id";

	/** 证件号 */
	public static final String IDENTITY_CODE = "identity_code";
	/** 证件号 */
	public static final String IDENTITY_HOLDER = "identity_holder";

	/** 接口类型 */
	public static final String INTERFACE_TYPE = "interface_type";

	/** 持卡人姓名 */
	public static final String CARD_HOLDER = "card_holder";
	/** 信用卡有效期 */
	public static final String VALID_DATE = "valid_date";
	/** 信用卡CVN2/CVV2 */
	public static final String CVV2 = "cvv2";
	/** 分账数据 */
	public static final String SPLIT_DATA = "split_data";
	/** 分账类型 */
	public static final String SPLIT_TYPE = "split_type";
	/** 分账类别 */
	public static final String SPLIT_CATEGORY = "split_category";

	/** 返回码 */
	public static final String RET_CODE = "ret_code";
	/** 返回信息 */
	public static final String RET_MSG = "ret_msg";
	/** 收款方账户类型 */
	public static final String RECV_ACCOUNT_TYPE = "recv_account_type";
	/** 收款方账户属性 */
	public static final String RECV_BANK_ACC_PRO = "recv_bank_acc_pro";
	/** 收款方账号 */
	public static final String RECV_ACCOUNT = "recv_account";
	/** 收款方户名 */
	public static final String RECV_USER_NAME = "recv_user_name";
	/**
	 * 订单日期
	 */
	public static final String ORDERDATE = "orderDate";

	/** 解析后的分账子订单数据 */
	public static final String SPLITLIST = "splitList";
	/** 子商户号 */
	public static final String SUBMERID = "subMerId";
	/** 子订单号 */
	public static final String SUBORDERID = "subOrderId";

	/** 子机构交易号 */
	public static final String SUBTRACE = "subTrace";
	/** 版本号 */
	public static final String VERSION = "version";
	/** 用户IP地址 */
	public static final String USER_IP = "user_ip";
	/** 业务扩展信息 */
	public static final String EXPAND = "expand";
	/** 风控扩展信息 */
	public static final String RISK_EXPEND = "risk_expand";
	/** 短信验证码 */
	public static final String VERIFY_CODE = "verify_code";
	/** 短信验证码 */
	public static final String VERIFYCODE = "VERIFYCODE";

	/** 推送类型3:下发验证码短信 */
	public static final String PUSH_TYPE = "push_type";
	/** 验证码类 */
	public static final String SMS_TYPE = "sms_type";
	/** DB中交易流水 */
	public static final String DBTRACE = "TRACE";
	/** 银行商户单号 */
	public static final String BANK_TRACE = "bank_trace";

	/** jsp模拟页面名称 */
	public static final String PAGENAME = "pageName";
	/** 付款方式 */
	public static final String PAYMENT_MODE = "payment_mode";

	/** DB中订单号ORDERID */
	public static final String ORDERID = "ORDERID";
	/** DB中订单号MAINORDERID */
	public static final String MAINORDERID = "MAINORDERID";
	/** DB中订单号TRANSTATE */
	public static final String TRANSTATE = "TRANSTATE";

	/** DB中订单号TRADENO */
	public static final String TRADENO = "TRADENO";

	/** DB中订单号GOODSID */
	public static final String GOODSID = "GOODSID";
	/** DB中订单号MOBILEID */
	public static final String MOBILEID = "MOBILEID";
	/** DB中订单号ORDERDATE */
	public static final String DB2ORDERDATE = "ORDERDATE";
	/** DB中订单号PLATDATE */
	public static final String PLATDATE = "PLATDATE";
	/** DB中订单号AMOUNT */
	public static final String DB2AMOUNT = "AMOUNT";
	/** DB中订单号AMTTYPE */
	public static final String AMTTYPE = "AMTTYPE";
	/** DB中订单号BANKTYPE */
	public static final String BANKTYPE = "BANKTYPE";
	/** DB中订单号MERCHECKDATE */
	public static final String MERCHECKDATE = "MERCHECKDATE";
	/** DB中订单号FUNCODE */
	public static final String FUNCODE = "FUNCODE";
	/** DB中订单号TRANSTATE */
	public static final String BD2TRANSTATE = "TRANSTATE";
	/** DB中订单号PRODUCTID */
	public static final String DB2PRODUCTID = "PRODUCTID";
	/** DB中订单号REFUNDNO */
	public static final String REFUNDNO = "REFUNDNO";
	/** DB中订单号MODTIME */
	public static final String MODTIME = "MODTIME";
	/** DB中订单号MAINMERID */
	public static final String MAINMERID = "MAINMERID";

	/** 清算类型-分账明细对账文件 */
	public static final String SPLITDETAIL = "SPLITDETAIL";
	/** stdPay4.0对账查询条件 **/
	public static final String STDPAYWHERE = "transtate=0 and funcode in('P100','P110','P111','T110',"
			+ "'T100','T101','T111','P522')  and (bankid not like 'XE%') and (bankid not like 'JF%')";
	/** stdPay4.0Split对账查询条件 **/
	public static final String STDPAYSPLITWHERE = "MAINORDERID !='' and  transtate=0 and funcode "
			+ "in('P600','P602','T600','T602','T612') and (bankid not like 'XE%') and (bankid not like 'JF%')";
	public static final String PRODUCTID = "productID";
	

	/** 对账任务名 **/
	public static final String COL_ID = "stdPay4.0";
	/** 对账任务名 **/
	public static final String COL_IDSPLIT = "stdPay4.0Split";
	/** 对账文件下载路径 **/
	public static final String SETTLE_PATH = "settle_path";
	public static final String REQ_FRONT_PAGE_PAY_WEB2JSP = "web2_0.jsp";// web收银台模拟页面
	public static final String ATDPAY = "/usr/mpsp/collate3/admin.sh stdPay4.0 -d ";// 对账stdPay4.0
	public static final String ATDPAYSPLIT = "/usr/mpsp/collate3/admin.sh stdPay4.0Split -d ";// 分账对账stdPay4.0Split
	public static final String ATDPAYDir = "/AppData/mercheck/all/";// 对账文件生成路径

	static File directory = new File("");// 设定为当前文件夹
	public final static String SEPARATOR = System.getProperty("file.separator");
	public static String dataSourse = directory.getAbsolutePath() + SEPARATOR + "datasource" + SEPARATOR;
	public static String downloadDir = dataSourse + "download" + SEPARATOR;
}