-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 2019-01-06 16:38:48
-- 服务器版本： 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `livejq`
--

-- --------------------------------------------------------

--
-- 表的结构 `tb_ans`
--

DROP TABLE IF EXISTS `tb_ans`;
CREATE TABLE IF NOT EXISTS `tb_ans` (
  `aid` int(20) NOT NULL AUTO_INCREMENT,
  `qid` int(20) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `answer` text NOT NULL,
  `support` int(20) NOT NULL DEFAULT '0',
  `against` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_ans`
--

INSERT INTO `tb_ans` (`aid`, `qid`, `nickname`, `level`, `date`, `answer`, `support`, `against`) VALUES
(4, 1, 'livejq', '0', '2018-12-31 13:28:19', 'hi!!!', 0, 0),
(5, 1, 'livejq', '0', '2018-12-31 14:07:41', 'This is test codes!', 0, 0),
(6, 1, 'livejq', '0', '2018-12-31 14:21:05', '123456', 0, 0),
(7, 1, 'livejq', '0', '2018-12-31 15:29:34', '好啊啊', 0, 0),
(8, 1, '明明', '教师', '2018-12-31 15:55:15', '我是教师', 0, 0),
(9, 1, '明明', '教师', '2018-12-31 17:31:08', '哈哈哈！！！', 0, 0),
(10, 1, 'livejq', '0', '2019-01-02 15:20:21', 'zhl', 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_check`
--

DROP TABLE IF EXISTS `tb_check`;
CREATE TABLE IF NOT EXISTS `tb_check` (
  `chid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `tkcodes` varchar(50) NOT NULL,
  `cname` varchar(50) NOT NULL,
  `sid` varchar(100) NOT NULL,
  `sname` varchar(50) NOT NULL,
  `cutoff` datetime NOT NULL,
  `date` datetime DEFAULT NULL,
  `stkname` varchar(100) DEFAULT NULL,
  `path` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `score` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`chid`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_check`
--

INSERT INTO `tb_check` (`chid`, `tid`, `tkcodes`, `cname`, `sid`, `sname`, `cutoff`, `date`, `stkname`, `path`, `status`, `score`) VALUES
(25, 1, '30474900', '软件172', '201715030208', '朱洪龙', '2019-01-06 17:03:00', '2019-01-05 22:24:02', 'solution.txt', 'I:/data/30474900', 1, 0),
(26, 1, '30474900', '软件172', '2', '2', '2019-01-06 17:03:00', '2019-01-05 22:27:48', 'ajax.txt', 'I:/data/30474900', 1, 0),
(27, 1, '30474900', '软件172', '3', '3', '2019-01-06 17:03:00', '2019-01-05 22:28:10', 'importError.txt', 'I:/data/30474900', 1, 0),
(28, 1, '30474900', '软件172', '4', '4', '2019-01-06 17:03:00', '2019-01-05 22:29:02', 'ajax.txt', 'I:/data/30474900', 1, 0),
(29, 1, '30474900', '软件172', '5', '5', '2019-01-06 17:03:00', '2019-01-05 22:29:21', 'ajax.txt', 'I:/data/30474900', 1, 0),
(30, 1, '30474900', '软件172', '6', '6', '2019-01-06 17:03:00', '2019-01-05 22:29:37', 'ajax.jpg', 'I:/data/30474900', 1, 0),
(31, 1, '30474900', '软件172', '7', '7', '2019-01-06 17:03:00', '2019-01-05 22:29:58', 'QQ截图20181208140207.png', 'I:/data/30474900', 1, 0),
(32, 1, '30474900', '软件172', '8', '8', '2019-01-06 17:03:00', '2019-01-05 22:30:12', 'QQ截图20181209211103.png', 'I:/data/30474900', 1, 0),
(33, 1, '30474900', '软件172', '9', '9', '2019-01-06 17:03:00', '2019-01-05 22:30:28', 'QQ截图20181208144022.png', 'I:/data/30474900', 1, 0),
(34, 1, '30474900', '软件172', '10', '10', '2019-01-06 17:03:00', '2019-01-05 22:30:54', '1925650-78a3e20cbfb4b864.webp', 'I:/data/30474900', 1, 0),
(35, 1, '30474900', '软件172', '11', '11', '2019-01-06 17:03:00', '2019-01-05 22:32:02', 'test1.html', 'I:/data/30474900', 1, 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_class`
--

DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE IF NOT EXISTS `tb_class` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `mid` int(11) NOT NULL,
  `cname` varchar(50) NOT NULL,
  `cnumber` int(11) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_class`
--

INSERT INTO `tb_class` (`cid`, `mid`, `cname`, `cnumber`) VALUES
(1, 1, '软件172', 11),
(2, 1, '软件171', 0),
(3, 2, '计科171', 0),
(4, 2, '计科172', 0),
(7, 2, '计科173', 0),
(8, 2, '计科174', 11);

-- --------------------------------------------------------

--
-- 表的结构 `tb_major`
--

DROP TABLE IF EXISTS `tb_major`;
CREATE TABLE IF NOT EXISTS `tb_major` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(50) NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_major`
--

INSERT INTO `tb_major` (`mid`, `mname`) VALUES
(1, '软件技术'),
(2, '计算机科学与技术');

-- --------------------------------------------------------

--
-- 表的结构 `tb_qst`
--

DROP TABLE IF EXISTS `tb_qst`;
CREATE TABLE IF NOT EXISTS `tb_qst` (
  `qid` int(20) NOT NULL AUTO_INCREMENT,
  `qtype` int(11) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `slevel` int(20) NOT NULL,
  `date` datetime NOT NULL,
  `title` varchar(100) NOT NULL,
  `details` text NOT NULL,
  `codes` text NOT NULL,
  `browse` int(20) NOT NULL DEFAULT '0',
  `answer` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`qid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_qst`
--

INSERT INTO `tb_qst` (`qid`, `qtype`, `nickname`, `slevel`, `date`, `title`, `details`, `codes`, `browse`, `answer`) VALUES
(1, 1, 'livejq', 0, '2018-12-29 16:08:00', 'super.init(config)加与不加有何区别？', '', 'public void init(ServletConfig config) throws ServletException {\r\n		super.init(config);\r\n	}', 0, 7),
(2, 1, '东莞', 0, '2019-01-04 12:53:00', '123124', '13123124', '432423423', 0, 0);

-- --------------------------------------------------------

--
-- 表的结构 `tb_qtype`
--

DROP TABLE IF EXISTS `tb_qtype`;
CREATE TABLE IF NOT EXISTS `tb_qtype` (
  `qtype` int(11) NOT NULL AUTO_INCREMENT,
  `qvalue` varchar(50) NOT NULL,
  PRIMARY KEY (`qtype`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_qtype`
--

INSERT INTO `tb_qtype` (`qtype`, `qvalue`) VALUES
(1, 'java'),
(2, 'android'),
(3, 'linux'),
(4, 'web'),
(5, 'arithmetic'),
(6, 'database');

-- --------------------------------------------------------

--
-- 表的结构 `tb_score`
--

DROP TABLE IF EXISTS `tb_score`;
CREATE TABLE IF NOT EXISTS `tb_score` (
  `scoreid` varchar(50) NOT NULL,
  `score` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_score`
--

INSERT INTO `tb_score` (`scoreid`, `score`) VALUES
('很差', 1),
('及格', 3),
('一般', 5),
('良好', 8),
('优秀', 10);

-- --------------------------------------------------------

--
-- 表的结构 `tb_slevel`
--

DROP TABLE IF EXISTS `tb_slevel`;
CREATE TABLE IF NOT EXISTS `tb_slevel` (
  `slevel` int(20) NOT NULL AUTO_INCREMENT,
  `svalue` int(20) NOT NULL,
  PRIMARY KEY (`slevel`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_slevel`
--

INSERT INTO `tb_slevel` (`slevel`, `svalue`) VALUES
(1, 20),
(2, 40),
(3, 60),
(4, 80);

-- --------------------------------------------------------

--
-- 表的结构 `tb_sqst`
--

DROP TABLE IF EXISTS `tb_sqst`;
CREATE TABLE IF NOT EXISTS `tb_sqst` (
  `sid` varchar(50) NOT NULL,
  `qid` int(20) NOT NULL,
  `sqid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`sqid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_sqst`
--

INSERT INTO `tb_sqst` (`sid`, `qid`, `sqid`) VALUES
('201715030208', 1, 1),
('201715210421', 2, 2);

-- --------------------------------------------------------

--
-- 表的结构 `tb_student`
--

DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE IF NOT EXISTS `tb_student` (
  `sid` varchar(50) NOT NULL,
  `spassword` varchar(50) NOT NULL,
  `sname` varchar(50) NOT NULL,
  `snickname` varchar(50) NOT NULL,
  `cid` int(11) NOT NULL,
  `slevel` int(11) NOT NULL DEFAULT '0',
  `sexperience` varchar(50) NOT NULL DEFAULT '0/20',
  `sactive` int(20) NOT NULL DEFAULT '0',
  `sexpvalue` int(11) NOT NULL DEFAULT '0',
  `svalue` int(20) NOT NULL DEFAULT '20'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_student`
--

INSERT INTO `tb_student` (`sid`, `spassword`, `sname`, `snickname`, `cid`, `slevel`, `sexperience`, `sactive`, `sexpvalue`, `svalue`) VALUES
('201715030208', '123456', '朱洪龙', 'livejq', 1, 0, '0/20', 0, 0, 20),
('2', '2', '2', '2', 1, 0, '0/20', 0, 0, 20),
('3', '3', '3', '3', 1, 0, '0/20', 0, 0, 20),
('4', '4', '4', '4', 1, 0, '0/20', 0, 0, 20),
('5', '5', '5', '5', 1, 0, '0/20', 0, 0, 20),
('6', '6', '6', '6', 1, 0, '0/20', 0, 0, 20),
('7', '7', '7', '7', 1, 0, '0/20', 0, 0, 20),
('8', '8', '8', '8', 1, 0, '0/20', 0, 0, 20),
('9', '9', '9', '9', 1, 0, '0/20', 0, 0, 20),
('10', '10', '10', '10', 1, 0, '0/20', 0, 0, 20),
('11', '11', '11', '11', 1, 0, '0/20', 0, 0, 20);

-- --------------------------------------------------------

--
-- 表的结构 `tb_task`
--

DROP TABLE IF EXISTS `tb_task`;
CREATE TABLE IF NOT EXISTS `tb_task` (
  `tkid` int(11) NOT NULL AUTO_INCREMENT,
  `tid` int(11) NOT NULL,
  `tname` varchar(50) NOT NULL,
  `cid` int(11) NOT NULL,
  `tkcodes` varchar(50) NOT NULL,
  `tktitle` varchar(500) NOT NULL,
  `tkcontent` text NOT NULL,
  `attach` varchar(100) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `date` datetime NOT NULL,
  `deadline` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tkid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_task`
--

INSERT INTO `tb_task` (`tkid`, `tid`, `tname`, `cid`, `tkcodes`, `tktitle`, `tkcontent`, `attach`, `path`, `date`, `deadline`, `status`) VALUES
(4, 1, '小明', 1, '30474900', '期末大作业', '交一份报告，附带jsp程序项目代码、数据库脚本和一小段录制的项目运行时的效果视屏。', 'mvc和dao.docx', 'I:/data', '2019-01-05 17:03:26', 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `tb_teacher`
--

DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE IF NOT EXISTS `tb_teacher` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `taccount` varchar(50) NOT NULL,
  `tpassword` varchar(50) NOT NULL,
  `tname` varchar(50) NOT NULL,
  `tnickname` varchar(50) NOT NULL,
  `tlevel` varchar(50) NOT NULL DEFAULT '普通教师',
  `texperience` varchar(50) NOT NULL DEFAULT '0/50',
  `tactive` int(20) NOT NULL DEFAULT '0',
  `texpvalue` int(20) NOT NULL DEFAULT '0',
  `tvalue` int(20) NOT NULL DEFAULT '50',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_teacher`
--

INSERT INTO `tb_teacher` (`tid`, `taccount`, `tpassword`, `tname`, `tnickname`, `tlevel`, `texperience`, `tactive`, `texpvalue`, `tvalue`) VALUES
(1, 'admin', 'admin', '小明', '明明', '普通教师', '0/20', 0, 0, 20);

-- --------------------------------------------------------

--
-- 表的结构 `tb_tlevel`
--

DROP TABLE IF EXISTS `tb_tlevel`;
CREATE TABLE IF NOT EXISTS `tb_tlevel` (
  `tlevel` varchar(50) NOT NULL,
  `tvalue` int(20) NOT NULL,
  PRIMARY KEY (`tlevel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_tlevel`
--

INSERT INTO `tb_tlevel` (`tlevel`, `tvalue`) VALUES
('中级教师', 20),
('博士', 200),
('教授', 120),
('高级教师', 60);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
