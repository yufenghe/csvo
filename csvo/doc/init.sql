/*
Navicat MySQL Data Transfer

Source Server         : 172.16.22.199
Source Server Version : 50535
Source Host           : 172.16.22.199:3306
Source Database       : sms

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-05-13 10:22:34
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `SMS_DETAIL_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `SMS_DETAIL_INFO`;
CREATE TABLE `SMS_DETAIL_INFO` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `syscode` varchar(30) DEFAULT NULL COMMENT '业务系统代码',
  `sendtime` datetime DEFAULT NULL COMMENT '发送时间',
  `sendcontent` varchar(300) DEFAULT NULL COMMENT '发送内容',
  `spid` varchar(255) DEFAULT NULL COMMENT '服务提供商代码',
  `serverip` varchar(50) DEFAULT NULL COMMENT '服务器ip',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `type` smallint(3) DEFAULT NULL COMMENT '发送类型，1:用户主动获取 2：系统推送',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=716 DEFAULT CHARSET=utf8;

-- ----------------------------
-- date: 20151216
--
-- 短信下行表添加拆分短信条数字段
-- ----------------------------
alter table SMS_DETAIL_INFO add totalpart int(2) comment '短信拆分的条数';

-- ----------------------------
-- date: 20151223
-- 
-- 增加短信发送标识字段   
-- ----------------------------
alter table SMS_DETAIL_INFO add `spmtid` varchar(50) DEFAULT NULL COMMENT '短信下行运营商返回的Id';




-- ----------------------------
-- Table structure for `SMS_UP_DATA`
-- ----------------------------
DROP TABLE IF EXISTS `SMS_UP_DATA`;
CREATE TABLE `SMS_UP_DATA` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `moid` varchar(50) DEFAULT NULL,
  `special` varchar(15) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `content` varchar(300) DEFAULT NULL,
  `sendtime` varchar(30) DEFAULT NULL COMMENT '上行短信记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `SMS_VOICE_DETAIL_INFO`
-- add at 2016-01-04
-- ----------------------------
DROP TABLE IF EXISTS `SMS_VOICE_DETAIL_INFO`;
CREATE TABLE `SMS_VOICE_DETAIL_INFO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `syscode` varchar(30) DEFAULT NULL COMMENT '业务系统代码',
  `sendtime` datetime DEFAULT NULL COMMENT '发送时间',
  `apsendtime` datetime DEFAULT NULL COMMENT '定时时间',
  `checkcode` varchar(6) DEFAULT NULL COMMENT '发送内容',
  `spid` varchar(255) DEFAULT NULL COMMENT '服务提供商代码',
  `serverip` varchar(50) DEFAULT NULL COMMENT '服务器ip',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `deliver` varchar(20) DEFAULT NULL COMMENT '服务提供商返回结果',
  `smsid` bigint DEFAULT NULL COMMENT '序列ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ----------------------------
-- date: 20160108
-- 新增状态报告表
-- Table structure for `SMS_MT_REPORT`
-- ----------------------------
DROP TABLE IF EXISTS `SMS_MT_REPORT`;
CREATE TABLE `SMS_MT_REPORT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `spid` varchar(255) DEFAULT NULL COMMENT '服务提供商代码   如：mandao/emay',
  `spmtid` varchar(50) DEFAULT NULL COMMENT '短信下行运营商返回的Id',
  `spsendtime` datetime DEFAULT NULL COMMENT '运营商发送时间',
  `updatetime` datetime DEFAULT NULL COMMENT '记录状态报告时间',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `spmtnumber` varchar(30) DEFAULT NULL COMMENT '短信下行特服号',
  `state` varchar(50) DEFAULT NULL COMMENT 'mandao:0或DELIVRD为成功  其他均为发送失败    emay:DELIVRD成功  其他均为发送失败 ',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

