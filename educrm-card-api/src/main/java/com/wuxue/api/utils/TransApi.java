package com.wuxue.api.utils;

/**
 * @date 2018/7/10下午9:55
 */
public interface TransApi {

    /***
     * GET  获取交易明细(无分页信息)  开始，结束时间必传
     *@param Trans
     * POST  新增交易记录
     */
    String trans_url = "http://61.175.187.54:8081/ascardapi/api/v1/trans";

    /***
     * 获取交易明细(有分页信息) 页数，大小，开始，结束时间必传
     *@param Trans
     * GET
     */
    String trans_list_url = "http://61.175.187.54:8081/ascardapi/api/v1/trans/list";


    /***
     * 获取門禁(有分页信息) 页数，大小，开始，结束时间必传
     *@param Trans
     * GET
     */
    String doors_list_url = "http://61.175.187.54:8081/ascardapi/api/v1/doors/list";

    /***
     * GET  获取考勤明细(无分页信息)  开始，结束时间必传
     *@param Trans
     * POST  新增考勤记录
     */
    String attendance_url = "http://61.175.187.54:8081/ascardapi/api/v1/kqs";

    /***
     * 获取考勤明细(有分页信息) 页数，大小，开始，结束时间必传
     *@param Trans
     * GET
     */
    String attendance_list_url = "http://61.175.187.54:8081/ascardapi/api/v1/kqs/list";

    /***
     * 获取賬戶信息
     *@param Trans
     * GET
     */
    String accounts_url = "http://61.175.187.54:8081/ascardapi/api/v1/accounts/";

    /***
     * 获取賬戶信息
     *@param Trans
     * GET
     */
    String student_url = "http://61.175.187.54:8081/ascardapi/api/v1/thirdpart/accounts/";

    /***
     * 新增賬戶信息
     *@param Trans
     * POST
     */
    String add_account_url = "http://61.175.187.54:8081/ascardapi/api/v1/thirdpart/accounts";

    /***
     * 新增賬戶信息list
     *@param Trans
     * POST
     */
    String add_accountlist_url = "http://61.175.187.54:8081/ascardapi/api/v1/thirdpart/accounts/list";

}
