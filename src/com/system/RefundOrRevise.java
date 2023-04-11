package com.system;

import com.exception.ReviseException;
import com.readjson.Station;

//import com.readjson.*;

public class RefundOrRevise {
	Booking booking;
	Station st = new Station();

	public RefundOrRevise(Booking bk) {
		booking = bk;
	}

	public void cancelTicket(String TrackNO) {
		String name = "No" + Booking.bookingInfo.get(TrackNO).getTrainNo1() + "_"
				+ booking.bookingInfo.get(TrackNO).getDate1();
		int ticketnum = booking.bookingInfo.get(TrackNO).getAdult1() + booking.bookingInfo.get(TrackNO).getCollege1();

		if (booking.bookingInfo.get(TrackNO).getDate2() == null) {// ��{
			if (ticketnum == 1) {// ��i�ʲ�
				booking.train.get(name).freeOneSeat(booking.bookingInfo.get(TrackNO).getSeat1());
				booking.bookingInfo.remove(TrackNO);
				System.out.println("�h�����\�A�w�����z���q�����");
			} else {// �h�h�i��
					// String name = "No" + bookingInfo.get(TrackNO).getTrainNo1() + "_" +
					// bookingInfo.get(TrackNO).getDate1();
				booking.train.get(name).freeMultipleSeats(booking.bookingInfo.get(TrackNO).getSeats1());
				booking.bookingInfo.remove(TrackNO);
				System.out.println("�h�����\�A�w�����z���q�����");
			}
		} else {// �Ӧ^��
			String name2 = "No" + booking.bookingInfo.get(TrackNO).getTrainNo2() + "_"
					+ booking.bookingInfo.get(TrackNO).getDate2();
			if (ticketnum == 1) {// ��i
				booking.train.get(name).freeOneSeat(booking.bookingInfo.get(TrackNO).getSeat1());
				booking.train.get(name2).freeOneSeat(booking.bookingInfo.get(TrackNO).getSeat2());
				booking.bookingInfo.remove(TrackNO);
				System.out.println("�h�����\�A�w�����z���q�����");

			} else {// �h�i
				booking.train.get(name).freeMultipleSeats(booking.bookingInfo.get(TrackNO).getSeats1());
				booking.train.get(name2).freeMultipleSeats(booking.bookingInfo.get(TrackNO).getSeats2());
				booking.bookingInfo.remove(TrackNO);
				System.out.println("�h�����\�A�w�����z���q�����");
			}

		}
	}

	public void reviseTicket(String trackNo, int adult, int college) throws ReviseException {
		String name = "No" + booking.bookingInfo.get(trackNo).getTrainNo1() + "_"
				+ booking.bookingInfo.get(trackNo).getDate1();
		int originalAdultNum = booking.bookingInfo.get(trackNo).getAdult1();
		int originalCollegeNum = booking.bookingInfo.get(trackNo).getCollege1();
		int ticketNum = originalAdultNum + originalCollegeNum;
		int cancelNum = adult + college;
		int leftNum = ticketNum - cancelNum;
		if ((originalAdultNum - adult == 0) && (originalCollegeNum - college == 0)) {
			throw new ReviseException("���ưh���Шϥΰh���\��A�Э��s��J�ο�JR�H��^");
//			System.out.println("���ưh���ШϥΡA�h���\��");
		}else if(leftNum < 0 || adult > originalAdultNum || college > originalCollegeNum) {
			throw new ReviseException("�h���i�ƿ��~�A�U���ذh���i�Ƥ����W�L��q�ʱi�ơA�Э��s��J�ο�JR�H��^");
		}else {
			if (booking.bookingInfo.get(trackNo).getDate2() == null) {// ��{
				String[] originalseat = booking.bookingInfo.get(trackNo).getSeats1();
				if (cancelNum == 1) {// �u�h�F�@�i��
					String cancelseat = originalseat[1];
					booking.train.get(name).freeOneSeat(cancelseat);
					if (leftNum == 1) {// �ѤU�@�i
						String leftseat = originalseat[originalseat.length - 1];
						booking.bookingInfo.get(trackNo).setSeat1(leftseat);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeats1(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.println("�y��s��: " + booking.bookingInfo.get(trackNo).getSeat1());
					} else {// �ѤU�h�i
						String[] leftseats = new String[ticketNum - cancelNum];
						for (int i = 1; i < originalseat.length; i++) {
							leftseats[i - 1] = originalseat[i];
						}
						booking.bookingInfo.get(trackNo).setSeats1(leftseats);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeat1(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j���u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.print("�y��s��: ");
						for (int i = 0; i < leftseats.length; i++) {
							System.out.print(leftseats[i] + " ");
						}
					}
				} else {// �h�h�i��{��
					String[] cancelseats = new String[cancelNum];
					for (int i = 0; i < cancelNum; i++) {
						cancelseats[i] = originalseat[i];
					}
					booking.train.get(name).freeMultipleSeats(cancelseats);
					if (leftNum == 1) {// �ѤU�@�i
						String leftseat = originalseat[originalseat.length - 1];
						booking.bookingInfo.get(trackNo).setSeat1(leftseat);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeats1(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.println("�y��s��: " + booking.bookingInfo.get(trackNo).getSeat1());
					} else {// �ѤU�h�i
						String[] leftseats = new String[leftNum];
						for (int i = leftNum; i < originalseat.length; i++) {
							leftseats[i - leftNum] = originalseat[i];
						}
						booking.bookingInfo.get(trackNo).setSeats1(leftseats);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeat1(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.print("�y��s��: ");
						for (int i = 0; i < leftseats.length; i++) {
							System.out.print(leftseats[i] + " ");
						}
					}

				}
			} else {// �Ӧ^
				String name2 = "No" + booking.bookingInfo.get(trackNo).getTrainNo2() + "_"
						+ booking.bookingInfo.get(trackNo).getDate2();
				String[] originalseat = booking.bookingInfo.get(trackNo).getSeats1();
				String[] originalseat2 = booking.bookingInfo.get(trackNo).getSeats2();
				if (cancelNum == 1) {// �u�h�F�@�i��
					String cancelseat = originalseat[1];
					String cancelseat2 = originalseat2[1];
					booking.train.get(name).freeOneSeat(cancelseat);
					booking.train.get(name2).freeOneSeat(cancelseat2);
					if (leftNum == 1) {// �ѤU�@�i
						String leftseat = originalseat[originalseat.length - 1];
						String leftseat2 = originalseat2[originalseat2.length - 1];
						booking.bookingInfo.get(trackNo).setSeat1(leftseat);
						booking.bookingInfo.get(trackNo).setSeat2(leftseat2);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeats1(null);
						booking.bookingInfo.get(trackNo).setSeats2(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.println("�y��s��: " + booking.bookingInfo.get(trackNo).getSeat1());
						System.out.println("");
						System.out.println("-------------------------------");
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate2());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo2());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart2()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime2() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd2()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime2());
						System.out.println("�y��s��: " + booking.bookingInfo.get(trackNo).getSeat2());
					} else {// �ѤU�h�i
						String[] leftseats = new String[ticketNum - cancelNum];
						for (int i = 1; i < originalseat.length; i++) {
							leftseats[i - 1] = originalseat[i];
						}
						String[] leftseats2 = new String[ticketNum - cancelNum];
						for (int i = 1; i < originalseat2.length; i++) {
							leftseats2[i - 1] = originalseat2[i];
						}
						booking.bookingInfo.get(trackNo).setSeats1(leftseats);
						booking.bookingInfo.get(trackNo).setSeats2(leftseats2);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeat1(null);
						booking.bookingInfo.get(trackNo).setSeat2(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.print("�y��s��: ");
						for (int i = 0; i < leftseats.length; i++) {
							System.out.print(leftseats[i] + " ");
						}
						System.out.println("");
						System.out.println("-------------------------------");
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate2());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo2());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart2()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime2() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd2()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime2());
						System.out.print("�y��s��: ");
						for (int i = 0; i < leftseats2.length; i++) {
							System.out.print(leftseats2[i] + " ");
						}
					}
				} else {// �h�h�i��{��
					String[] cancelseats = new String[cancelNum];
					for (int i = 0; i < cancelNum; i++) {
						cancelseats[i] = originalseat[i];
					}
					String[] cancelseats2 = new String[cancelNum];
					for (int i = 0; i < cancelNum; i++) {
						cancelseats2[i] = originalseat2[i];
					}
					booking.train.get(name).freeMultipleSeats(cancelseats);
					booking.train.get(name2).freeMultipleSeats(cancelseats2);
					if (leftNum == 1) {// �ѤU�@�i
						String leftseat = originalseat[originalseat.length - 1];
						String leftseat2 = originalseat2[originalseat2.length - 1];
						booking.bookingInfo.get(trackNo).setSeat1(leftseat);
						booking.bookingInfo.get(trackNo).setSeat2(leftseat2);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeats1(null);
						booking.bookingInfo.get(trackNo).setSeats2(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.println("�y��s��: " + booking.bookingInfo.get(trackNo).getSeat1());
						System.out.println("");
						System.out.println("-------------------------------");
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate2());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo2());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart2()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime2() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd2()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime2());
						System.out.println("�y��s��: " + booking.bookingInfo.get(trackNo).getSeat2());
					} else {// �ѤU�h�i
						String[] leftseats = new String[leftNum];
						for (int i = leftNum; i < originalseat.length; i++) {
							leftseats[i - leftNum] = originalseat[i];
						}
						String[] leftseats2 = new String[leftNum];
						for (int i = leftNum; i < originalseat2.length; i++) {
							leftseats2[i - leftNum] = originalseat2[i];
						}
						booking.bookingInfo.get(trackNo).setSeats1(leftseats);
						booking.bookingInfo.get(trackNo).setSeats2(leftseats2);
						booking.bookingInfo.get(trackNo).setAdult1(originalAdultNum - adult);
						booking.bookingInfo.get(trackNo).setCollege1(originalCollegeNum - college);
						booking.bookingInfo.get(trackNo).setSeat1(null);
						booking.bookingInfo.get(trackNo).setSeat2(null);
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate1());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo1());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart1()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime1() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd1()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime1());
						System.out.print("�y��s��: ");
						for (int i = 0; i < leftseats.length; i++) {
							System.out.print(leftseats[i] + " ");
						}
						System.out.println("");
						System.out.println("-------------------------------");
						System.out.println("���: " + booking.bookingInfo.get(trackNo).getDate2());
						System.out.println("���H��: " + booking.bookingInfo.get(trackNo).getAdult1());
						System.out.println("�j�ǥ��u�f��: " + booking.bookingInfo.get(trackNo).getCollege1());
						System.out.println("����: " + booking.bookingInfo.get(trackNo).getTrainNo2());
						System.out.println(st.getChStationName(booking.bookingInfo.get(trackNo).getStart2()) + " "
								+ booking.bookingInfo.get(trackNo).getDepartureTime2() + " -> "
								+ st.getChStationName(booking.bookingInfo.get(trackNo).getEnd2()) + " "
								+ booking.bookingInfo.get(trackNo).getArrivalTime2());
						System.out.print("�y��s��: ");
						for (int i = 0; i < leftseats2.length; i++) {
							System.out.print(leftseats2[i] + " ");
						}
					}
				}
			}
		}
	}
}