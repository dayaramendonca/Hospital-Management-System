-- phpMyAdmin SQL Dump
-- version 4.0.10.14
-- http://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Sep 12, 2016 at 08:49 AM
-- Server version: 5.5.49-cll-lve
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hospitalbd`
--

-- --------------------------------------------------------

--
-- Table structure for table `admission`
--

CREATE TABLE IF NOT EXISTS `admission` (
  `admission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patient_id` bigint(20) NOT NULL,
  `consulting_doctor_id` bigint(20) NOT NULL,
  `ward_no` varchar(50) NOT NULL,
  `cabin_no` varchar(50) NOT NULL,
  `admission_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `discharge_date` timestamp NULL DEFAULT NULL,
  `note_if_any` varchar(256) NOT NULL,
  `is_discharged` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`admission_id`),
  UNIQUE KEY `admission_id` (`admission_id`),
  KEY `patient_id` (`patient_id`),
  KEY `examining_doctor_id` (`consulting_doctor_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `admission`
--

INSERT INTO `admission` (`admission_id`, `patient_id`, `consulting_doctor_id`, `ward_no`, `cabin_no`, `admission_date`, `discharge_date`, `note_if_any`, `is_discharged`) VALUES
(1, 12, 5, '4', '9', '2016-09-05 11:35:21', '2016-09-05 11:35:21', '', 1),
(2, 12, 5, '4', '9', '2016-09-05 11:37:15', NULL, '', 0),
(3, 11, 19, '5', '6', '2016-09-05 11:38:09', NULL, '', 0),
(4, 11, 19, '5', '6', '2016-09-05 11:39:49', '2016-09-11 04:39:40', '', 1),
(5, 17, 4, '5', '3', '2016-09-06 01:19:38', '2016-09-06 01:20:32', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `all_users`
--

CREATE TABLE IF NOT EXISTS `all_users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `role_type_id` int(2) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sex` varchar(20) NOT NULL DEFAULT 'male',
  `address` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `speciality` varchar(30) NOT NULL,
  `age` int(3) NOT NULL,
  `password` varchar(256) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `is_fired` tinyint(1) NOT NULL DEFAULT '0',
  `current_status` varchar(300) NOT NULL,
  `admin_notice` varchar(500) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `role_type_id` (`role_type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `all_users`
--

INSERT INTO `all_users` (`user_id`, `username`, `role_type_id`, `name`, `sex`, `address`, `phone`, `speciality`, `age`, `password`, `is_active`, `is_fired`, `current_status`, `admin_notice`) VALUES
(1, 'admin1', 1, 'Super Admin1', 'male', 'Dhanmondi, Dhaka', '01711111111', 'Admin', 59, '123456', 1, 0, 'working', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(2, 'admin2', 2, 'Sub Admin1', 'male', 'Banani, Dhaka', '01911111111', 'SubAdmin', 44, '123456', 1, 0, 'In a leave for 8 days', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(3, 'admin3', 2, 'Sub Admin2', 'female', 'Uttara, Dhaka', '01911111112', 'SubAdmin', 39, '123456', 1, 1, 'STAFF FIRED BY ADMIN!', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(4, 'doctor1', 3, 'Specialist Doctor1', 'male', 'Mirpur, Dhaka', '01611111111', 'Medicine Specialist', 47, '123456', 1, 0, 'visiting outdoor', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(5, 'doctor2', 3, 'Specialist Doctor2', 'male', 'Gulisthan, Dhaka', '01611111112', 'Surgical Specialist', 60, '123456', 1, 0, 'Brain Surgery at OT-3', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(6, 'doctor3', 4, 'Investigating Doctor1', 'male', 'Tongi, Gazipur', '01511111111', 'Radiologist', 37, '123456', 1, 0, 'Investigating patient4', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(7, 'doctor4', 4, 'Investigating Doctor2', 'female', 'Farmgate, Dhaka', '01511111112', 'Pathologist', 48, '123456', 1, 0, 'investigating patient6', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(8, 'pharm1', 5, 'Pharmacist1', 'male', 'Mirpur, Dhaka', '01511111113', 'Pharmacist', 31, '123456', 1, 0, 'serving visitors', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(9, 'patient1', 6, 'Mr. Patient1', 'male', 'Gulshan, Dhaka', '01811111111', 'Patient', 42, '123456', 1, 0, 'visiting doctor2', ''),
(10, 'patient2', 6, 'Mrs. Patient2', 'female', 'Uttara, Dhaka', '01811111112', 'Patient', 32, '123456', 1, 0, 'visiting pharmacy', ''),
(11, 'patient3', 6, 'Mr. Patient3', 'male', 'Shamoly, Dhaka', '01811111113', 'Patient', 29, '123456', 1, 0, 'having operation', ''),
(12, 'patient4', 6, 'Mrs. Patient4', 'female', 'Mohammadpur, Dhaka', '01811111114', 'Patient', 63, '123456', 1, 0, 'Please wait for 5 minutes', ''),
(13, 'doctor5', 3, 'Specialist Doctor3', 'female', 'Savar, Dhaka', '01611111113', 'Eye Specialist', 48, '123456', 1, 0, 'investigating patient9', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(14, 'doctor6', 3, 'Specialist Doctor4', 'male', 'Mirpur, Dhaka', '01611111114', 'ENT Specialist', 60, '123456', 1, 0, 'free', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(15, 'doctor7', 3, 'Specialist Doctor5', 'male', 'Mohakhali, Dhaka', '01611111115', 'Dental Surgeon ', 50, '123456', 1, 0, 'outside', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(16, 'pharm2', 5, 'Pharmacist2', 'female', 'Nil Sagar, Manikganj', '01522211111', 'Pharmacist', 49, '123456', 1, 0, 'Active', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(17, 'patient5', 6, 'Mr. Patient5', 'male', 'Nabinagar, Savar', '01822211112', 'Patient', 17, '123456', 1, 0, 'Waiting for doctor', ''),
(18, 'patient6', 6, 'Miss. Patient6', 'female', 'Badda, Dhaka', '01833311112', 'Patient', 26, '123456', 1, 0, 'Visit Room 12', ''),
(19, 'doctor8', 3, 'Specialist Doctor6', 'female', 'Baridhara, Dhaka', '01622211112', 'Gynecologist', 38, '123456', 1, 1, 'STAFF FIRED BY ADMIN!', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(20, 'doctor9', 3, 'Specialist Doctor7', 'male', 'Kalabagan, Dhaka', '01633311112', 'Medicine Specialist', 35, '123456', 1, 0, 'Attending a meeting', 'Please attend conference room now.\nDate: 12-9-16\nTime: 11:00 AM'),
(21, 'patient7', 6, 'Mr. Patient7', 'male', 'Borobazar, Rangpur', '01911200990', 'Patient', 45, '123', 1, 0, 'Please come tomorrow', ''),
(22, 'doctor12', 3, 'Specialist Doctor13', 'Female', 'Kunja, Chittagong', '01887654434', 'Gynea & Obs', 38, '123456', 1, 0, '', ''),
(25, 'doctor14', 4, 'Investigating Doctor5', 'male', 'Rampura, Dhaka', '01556323875', 'Lab Specialist', 34, '123456', 1, 0, '', ''),
(26, 'staff1', 2, 'Ggxhhhh', 'Male', 'Mymensibgh ', '01866555522', 'Supervisor', 65, '123456', 1, 0, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `appointment_requests`
--

CREATE TABLE IF NOT EXISTS `appointment_requests` (
  `request_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `person_name` varchar(100) NOT NULL,
  `sex` varchar(20) NOT NULL,
  `age` varchar(20) NOT NULL,
  `problem_details` varchar(1000) NOT NULL,
  `phone_no` varchar(100) NOT NULL,
  `request_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_responded` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`request_id`),
  UNIQUE KEY `request_id` (`request_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `appointment_requests`
--

INSERT INTO `appointment_requests` (`request_id`, `person_name`, `sex`, `age`, `problem_details`, `phone_no`, `request_date`, `is_responded`) VALUES
(1, 'Mr xyz abc', 'male', '38', 'High Fever for 7 days', '01812983438', '2016-09-02 14:21:31', 0),
(2, 'Mrs. pqr sty', 'female', '55', 'Back Pain', '01518772216', '2016-09-02 14:22:39', 0),
(3, 'Zuabair Hossain', 'male', '44', 'High Fever, Headache', '01818887766', '2016-09-06 06:43:10', 1),
(4, 'Mizanur Rahman', 'Male', '30', 'Typhoid', '03456385691', '2016-09-06 06:45:17', 0),
(6, 'omar', 'M', '65', 'fever', '01111111555', '2016-09-12 12:44:51', 1);

-- --------------------------------------------------------

--
-- Table structure for table `emergency_info`
--

CREATE TABLE IF NOT EXISTS `emergency_info` (
  `emergency_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `emergency_officer` varchar(1000) NOT NULL,
  `ambulace_number` varchar(250) NOT NULL,
  `other_services` varchar(250) NOT NULL,
  UNIQUE KEY `emergency_id` (`emergency_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `investigation`
--

CREATE TABLE IF NOT EXISTS `investigation` (
  `investigation_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `patient_id` bigint(20) NOT NULL,
  `consulting_doctor` varchar(200) NOT NULL,
  `investigating_doctor` varchar(200) NOT NULL,
  `investigation_report` varchar(2000) NOT NULL,
  `time_and_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`investigation_id`),
  UNIQUE KEY `investigation_id` (`investigation_id`),
  KEY `patient_id` (`patient_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `investigation`
--

INSERT INTO `investigation` (`investigation_id`, `patient_id`, `consulting_doctor`, `investigating_doctor`, `investigation_report`, `time_and_date`) VALUES
(1, 12, 'Specialist Doctor-2', 'Investigating doctor-3', 'This is a sample Investigation Report, This is a sample Investigation Report, This is a sample Investigation Report.', '2016-08-28 15:35:22'),
(2, 12, 'Specialist Doctor-11', 'Investigating doctor-8', 'This is a sample Investigation Report, This is a sample Investigation Report, This is a sample Investigation Report.', '2016-08-17 02:20:51'),
(3, 11, 'Dr. Suraj', 'Dr.  Murad', 'X-ray report attached. ', '2016-09-11 16:26:26');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patient_id` bigint(20) NOT NULL,
  `payment_item_id` bigint(20) NOT NULL,
  `item_amount` varchar(50) NOT NULL,
  `due_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paid_date` datetime NOT NULL,
  `billing_note` varchar(500) NOT NULL,
  `is_paid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`payment_id`),
  UNIQUE KEY `payment_id` (`payment_id`),
  KEY `patient_id` (`patient_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `patient_id`, `payment_item_id`, `item_amount`, `due_date`, `paid_date`, `billing_note`, `is_paid`) VALUES
(1, 12, 1, '1000', '2016-08-31 02:01:49', '2016-08-31 12:50:14', '', 1),
(2, 12, 2, '1200', '2016-08-11 02:04:14', '2016-08-17 08:25:24', '', 1),
(4, 10, 1, '350', '2016-09-09 17:01:32', '0000-00-00 00:00:00', '', 0),
(5, 11, 2, '600', '2016-09-09 17:05:19', '2016-09-09 10:06:33', '', 1),
(6, 18, 4, '1100', '2016-09-09 18:46:47', '0000-00-00 00:00:00', '', 0),
(7, 18, 3, '1650', '2016-09-09 18:51:14', '0000-00-00 00:00:00', '', 0),
(9, 10, 3, '1400', '2016-09-10 02:09:29', '2016-09-10 09:58:53', '', 1),
(11, 11, 1, '250', '2016-09-10 02:19:07', '0000-00-00 00:00:00', '', 0),
(12, 18, 2, '4500', '2016-09-10 12:45:04', '2016-09-10 05:45:51', '', 1),
(13, 18, 3, '3500', '2016-09-11 15:07:32', '0000-00-00 00:00:00', 'All required medicines provided', 0),
(14, 11, 3, '1200', '2016-09-12 03:07:13', '0000-00-00 00:00:00', 'According to prescription ', 0),
(15, 18, 1, '500', '2016-09-12 13:01:25', '2016-09-12 06:02:26', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment_items`
--

CREATE TABLE IF NOT EXISTS `payment_items` (
  `payment_item_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `payment_item` varchar(100) NOT NULL,
  PRIMARY KEY (`payment_item_id`),
  UNIQUE KEY `payment_item_id` (`payment_item_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `payment_items`
--

INSERT INTO `payment_items` (`payment_item_id`, `payment_item`) VALUES
(1, 'Outdoor Amount'),
(2, 'Investigation Amount'),
(3, 'Pharmacy Bill'),
(4, 'Admission Charges'),
(5, 'Discharge Amount'),
(6, 'Extra Charges');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE IF NOT EXISTS `prescription` (
  `prescription_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patient_id` bigint(20) NOT NULL,
  `doctor_id` bigint(20) NOT NULL,
  `complain_details` varchar(512) NOT NULL,
  `patient_history` varchar(512) NOT NULL,
  `main_prescription` varchar(1000) NOT NULL,
  `doctors_advice` varchar(256) NOT NULL,
  `general_checkup` varchar(256) NOT NULL,
  `investigation1` varchar(128) NOT NULL,
  `investigation2` varchar(128) NOT NULL,
  `investigation3` varchar(128) NOT NULL,
  `time_and_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`prescription_id`),
  UNIQUE KEY `prescription_id` (`prescription_id`),
  KEY `patient_id` (`patient_id`),
  KEY `doctor_id` (`doctor_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`prescription_id`, `patient_id`, `doctor_id`, `complain_details`, `patient_history`, `main_prescription`, `doctors_advice`, `general_checkup`, `investigation1`, `investigation2`, `investigation3`, `time_and_date`) VALUES
(1, 12, 5, 'Headache, Less Sleep, Urine Problem, Feeling fever at night.', 'Suffering regular fever for last 10 years. Had tonsil operation on 2008.', 'Napa Paracetamol 3 times a day.\nBenjodol Aspenin once every day.', 'Take regular excercise.', 'BP : 150/90\r\nPulse : 80', 'Endoscopy', 'ECG', 'Born X-Ray', '2016-08-01 18:38:12'),
(2, 12, 15, 'Back Pain, fever, pain increase during morning hours.', 'had rheumatic fever in childhood.', 'Butapain tablet 5mg daily', 'Walk for a hour daily.', 'BP : 120/80\r\nPulse : 78', 'X-Ray', '', '', '2016-07-13 02:24:41'),
(3, 18, 3, 'Fever', 'Headache ', 'Take rest and take a medicine daily', 'Drink more water', 'BP : 160/90', 'ECG', '', '', '2016-09-11 16:31:19'),
(4, 17, 3, 'Dfgjjjjgvhhj', 'Ccghhhuxfhh', 'Cvggghhh', 'Vgghhjkjjhbh', 'Gtyucgjhgg', 'Drgcfggvghv', 'Cgucbgvvgyh', 'Vfghhhgftgghj Gghh ggg', '2016-09-11 16:47:38'),
(5, 10, 13, 'Loose Motion', 'N/A', 'SMC', 'Take water ', 'BP : 120/80', '', '', '', '2016-09-12 01:16:12');

-- --------------------------------------------------------

--
-- Table structure for table `role_type`
--

CREATE TABLE IF NOT EXISTS `role_type` (
  `role_type_id` int(2) NOT NULL,
  `role_type_name` varchar(50) NOT NULL,
  PRIMARY KEY (`role_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_type`
--

INSERT INTO `role_type` (`role_type_id`, `role_type_name`) VALUES
(1, 'super_admin'),
(2, 'sub_admin'),
(3, 'consulting_doctor'),
(4, 'investigating_doctor'),
(5, 'pharmacist'),
(6, 'patient');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
