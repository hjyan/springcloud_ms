package com.zyyan.ms.term.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;

/**
 * <p>
 * 预置任务表
 * </p>
 *
 * @author cli
 * @since 2018-05-17
 */
@TableName("DEV_SCHEDULE_TASK")
//@KeySequence(value = "TASK_ID", clazz = Integer.class)
public class ScheduleTask extends Model<ScheduleTask> {

	private static final long serialVersionUID = 1L;

	/**
	 * 任务编号
	 */
	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * TERM_SN
	 */
	@TableField("TERM_SN")
	private String termSn;
	/**
	 * 任务控制名称
	 */
	@TableField("CTRL_NAME")
	private String ctrlName;
	@TableField("CTRL_DESCRIPTION")
	private String ctrlDescription;
	/**
	 * 开始执行时间
	 */
	@TableField("EXEC_START_TIME")
	private Date execStartTime;
	/**
	 * 执行结束时间
	 */
	@TableField("EXEC_END_TIME")
	private Date execEndTime;
	/**
	 * 状态
	 */
	@TableField("STATUS")
	private Integer status;
	/**
	 * 最大失败执行次数
	 */
	@TableField("MAX_FAIL_COUNT")
	private Integer maxFailCount;
	/**
	 * 失败次数
	 */
	@TableField("FAIL_COUNT")
	private Integer failCount;
	/**
	 * 失败错误码
	 */
	@TableField("FAIL_CODE")
	private String failCode;
	/**
	 * 失败消息
	 */
	@TableField("FAIL_MSG")
	private String failMsg;
	/**
	 * 预置任务消息体
	 */
	@TableField("CONTENT")
	private String content;
	/**
	 * 任务执行进度
	 */
	@TableField("EXEC_PROCESS")
	private String execProcess;
	/**
	 * 策略编号
	 */
	@TableField("TACTICS_ID")
	private Integer tacticsId;
	
	@TableField("SOURCE")
	private String source;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTermSn() {
		return termSn;
	}

	public void setTermSn(String termSn) {
		this.termSn = termSn;
	}

	public String getCtrlName() {
		return ctrlName;
	}

	public void setCtrlName(String ctrlName) {
		this.ctrlName = ctrlName;
	}

	public String getCtrlDescription() {
		return ctrlDescription;
	}

	public void setCtrlDescription(String ctrlDescription) {
		this.ctrlDescription = ctrlDescription;
	}

	public Date getExecStartTime() {
		return execStartTime;
	}

	public void setExecStartTime(Date execStartTime) {
		this.execStartTime = execStartTime;
	}

	public Date getExecEndTime() {
		return execEndTime;
	}

	public void setExecEndTime(Date execEndTime) {
		this.execEndTime = execEndTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMaxFailCount() {
		return maxFailCount;
	}

	public void setMaxFailCount(Integer maxFailCount) {
		this.maxFailCount = maxFailCount;
	}

	public Integer getFailCount() {
		return failCount;
	}

	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}

	public String getFailCode() {
		return failCode;
	}

	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExecProcess() {
		return execProcess;
	}

	public void setExecProcess(String execProcess) {
		this.execProcess = execProcess;
	}

	public Integer getTacticsId() {
		return tacticsId;
	}

	public void setTacticsId(Integer tacticsId) {
		this.tacticsId = tacticsId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "DevScheduleTask{"
				+ ", id=" + id
				+ ", termSn=" + termSn
				+ ", ctrlName=" + ctrlName
				+ ", ctrlDescription=" + ctrlDescription
				+ ", execStartTime=" + execStartTime
				+ ", execEndTime=" + execEndTime
				+ ", status=" + status
				+ ", maxFailCount=" + maxFailCount
				+ ", failCount=" + failCount
				+ ", failCode=" + failCode
				+ ", failMsg=" + failMsg
				+ ", content=" + content
				+ ", execProcess=" + execProcess
				+ ", tacticsId=" + tacticsId
				+ "}";
	}
}
