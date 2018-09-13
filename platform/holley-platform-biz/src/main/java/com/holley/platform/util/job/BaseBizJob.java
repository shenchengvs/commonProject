package com.holley.platform.util.job;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.holley.platform.common.constants.Globals;
import com.holley.platform.common.pool.BaseJob;
import com.holley.platform.common.util.DateUtil;
import com.holley.platform.util.HostLogUtils;

/**
 * 具备日志记录功能job<br/>
 * 
 * @author zhouli
 */
public abstract class BaseBizJob extends BaseJob {

    static Log       log      = LogFactory.getLog(BaseBizJob.class);

    private Short    tasktype = Globals.TASK_AUTO;

    protected Long   taskId;                                        // 主键
    protected String moduleName;
    protected Date   beginTime;                                     // 开始运行时间
    protected Date   endTime;                                       // 停止时间

    protected Short  circleType;                                    // 统计周期
    protected short  objtype;                                       // 对象类型
    protected int    objid;                                         // 对象编码
    protected Date   startTime;                                     // 计算日期
    protected int    includeCalcSub;                                // 任是否包含下级计算

    private String getKey() {
        StringBuffer sb = new StringBuffer();
        sb.append(taskId == null ? "" : taskId).append(":");
        sb.append("objtype=").append(objtype).append(":");
        sb.append("objid=").append(objid).append(":");
        sb.append("startTime=").append(DateUtil.DateToLongStr(startTime)).append(":");
        return sb.toString();
    }

    @Override
    public void run() {
        beforeRun();
        try {
            execute();
        } catch (Exception e) {
            log.error(e.getMessage());
            HostLogUtils.recordCaclLog(moduleName, getKey() + e.getMessage());
            failRun();
            return;
        }
        afterRun();
    }

    public void afterRun() {
        super.afterRun();
        this.removeJob();
    }

    /**
     * 计算进度
     * 
     * @param totalCount 总数量
     * @param currentCount 当前数量
     * @param firstPercent 进度区间起
     * @param lastPercent 进度区间止
     * @return
     */
    public int calcPercent(int totalCount, int currentCount, int firstPercent, int lastPercent) {
        if (totalCount == 0) {
            return firstPercent;
        }

        int rate = lastPercent - firstPercent;

        return (rate * currentCount / totalCount) + firstPercent;

    }

    public void setObjtype(short objtype) {
        this.objtype = objtype;
    }

    public void setObjid(int objid) {
        this.objid = objid;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Short getTasktype() {
        return tasktype;
    }

    public void setTasktype(Short tasktype) {
        this.tasktype = tasktype;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Short getCircleType() {
        return circleType;
    }

    public void setCircleType(Short circleType) {
        this.circleType = circleType;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getIncludeCalcSub() {
        return includeCalcSub;
    }

    public void setIncludeCalcSub(int includeCalcSub) {
        this.includeCalcSub = includeCalcSub;
    }

}
