package com.moviement.controller;

import java.util.List;
import java.util.Scanner;

import com.moviement.container.Container;
import com.moviement.dto.Member;
import com.moviement.dto.Review;
import com.moviement.dto.Seat;
import com.moviement.service.MemberService;
import com.moviement.service.ReviewService;

public class MemberController extends Controller {
	private Scanner sc;
	private int selectNum;
	private MemberService memberService;
	private ReviewService reviewService;
	private Session session;

	public MemberController(Scanner isc) {
		this.sc = isc;
		memberService = Container.memberService;
		reviewService = Container.reviewService;
		session = Container.getSession();
	}

	public void doAction(int selectNum) {
		this.selectNum = selectNum;

		System.out.printf("=== === === M E M B E R === === ===\n\n");
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 로그아웃");
		System.out.println("4. 마이 페이지");
		System.out.printf("9. 이전 단계로\n\n");
		System.out.printf("선택 : ");
		selectNum = sc.nextInt();
		System.out.println();

		switch (selectNum) {
		case 1:
			doJoin();
			break;
		case 2:
			doLogin();
			break;
		case 3:
			doLogout();
			break;
		case 4:
			showMyPage();
			break;
		case 9:
			break;
		default:
			System.out.println("다시 입력해주세요.\n");
			break;
		}
	}

	private boolean isJoinableLoginId(String loginId) {
		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return true;
		}
		return false;
	}

	private boolean isJoinableEmail(String Email) {
		Member member = memberService.getMemberByLoginId(Email);

		if (member == null) {
			return true;
		}
		return false;
	}

	private boolean isJoinableNickName(String nickName) {
		Member member = memberService.getMemberBynickName(nickName);

		if (member == null) {
			return true;
		}
		return false;
	}

	private void doJoin() {
		if (Container.getSession().isLogined()) {
			System.out.println("이미 로그인 되어있습니다. 로그아웃 후 이용해주세요.\n");
			return;
		}
		String loginId = null;

		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.next();

			if (isJoinableLoginId(loginId) == false) {
				System.out.printf("%s(은)는 이미 사용중인 아이디입니다.\n", loginId);
				continue;
			}
			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.next();
			System.out.printf("비밀번호 확인 : ");
			loginPwConfirm = sc.next();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		String eMail = null;

		while (true) {
			System.out.printf("이메일 : ");
			eMail = sc.next();

			if (isJoinableEmail(eMail) == false) {
				System.out.printf("%s(은)는 이미 사용중인 이메일입니다.\n", eMail);
				continue;
			}
			break;
		}

		String nickName = null;

		while (true) {
			System.out.printf("닉네임 : ");
			nickName = sc.next();

			if (isJoinableNickName(nickName) == false) {
				System.out.printf("%s(은)는 이미 사용중인 닉네임입니다.\n", nickName);
				continue;
			}
			break;
		}

		System.out.printf("이름 : ");
		String name = sc.next();

		memberService.join(loginId, eMail, nickName, loginPw, name);

		System.out.printf("\n%s님, MovieMent 회원이 되신걸 환영합니다 :D\n\n", name);
	}

	public void doLogin() {
		if (Container.getSession().isLogined()) {
			System.out.println("이미 로그인 되어있습니다.\n");
			return;
		}
		String loginId;
		String loginPw;
		Member member;
		System.out.printf("=== === === L O G I N === === ===\n\n");
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.next();
			System.out.printf("비밀번호 : ");
			loginPw = sc.next();
			System.out.println();
			member = memberService.getMemberByLoginId(loginId);

			if (member == null) {
				System.out.println("해당 회원은 존재하지 않습니다.\n");
				continue;
			}

			if (member.loginPw.equals(loginPw) == false) {
				System.out.println("비밀번호를 다시 확인해주세요.\n");
				continue;
			}
			break;
		}

		session.setLoginedMember(member);
		Member loginedMember = session.getLoginedMember();

		System.out.printf("어서 오세요 %s님, 환영합니다 :D\n\n", loginedMember.name);
	}

	private void doLogout() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}
		session.setLoginedMember(null);
		System.out.println("로그아웃 되었습니다.\n");
	}

	private void showMyPage() {
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}

		System.out.printf("=== === === M Y P A G E === === ===\n\n");
		System.out.printf("1. 회원정보 수정\n");
		System.out.printf("2. 나의 예매 현황\n");
		System.out.printf("3. 나의 리뷰\n");
		System.out.printf("4. 회원 탈퇴\n");
		System.out.printf("9. 이전 단계로\n\n");
		System.out.printf("선택 : ");
		int selectNum = sc.nextInt();
		System.out.println();

		switch (selectNum) {
		case 1:
			doModify();
			break;
		case 2: // 예매 현황 구현하기 : Line 254
			showTicket();
			break;
		case 3:
			showReviewList();
			break;
		case 4:
			withdrawal();
			break;
		case 9:
			break;
		}
	}

	private void withdrawal() {
		
		if (Container.getSession().isLogined() == false) {
			System.out.println("로그인 후 이용해주세요.\n");
			return;
		}
		
		Member loginedMember = Container.getSession().getLoginedMember();
		System.out.printf("비밀번호를 입력하세요 : ");
		String loginPw = sc.next();
		
		if(loginedMember.loginPw.equals(loginPw)==false) {
			System.out.println("비밀번호가 틀렸습니다.");
			return;
		}
		memberService.delete(loginedMember.id);
		System.out.println("회원 탈퇴 하였습니다.");
		
	}

	private void doModify() {
		while (true) {
			System.out.println("=== === === 회원 정보 수정 === === ===\n");
			System.out.printf("1. 비밀번호 변경 \n");
			System.out.printf("2. 이메일 변경 \n");
			System.out.printf("3. 닉네임 변경 \n\n");
			System.out.printf("선택 : ");
			int selectNum = sc.nextInt();
			System.out.println();

			switch (selectNum) {
			case 1:
				changeLoginPw();
				break;
			case 2:
				changeEmail();
				break;
			case 3:
				changeNickName();
				break;
			}
			break;
		}
	}

	private void showTicket() {
		Member loginedMember = Container.getSession().getLoginedMember();
		List<Seat> getForPrintSeat = Container.seatService.getForPrintSeats(loginedMember.nickName);
		
		if (getForPrintSeat.size() == 0) {
			System.out.println("검색결과가 존재하지 않습니다.");
			return;
		}

		System.out.printf("=== === === 나의 예매 현황 === === ===\n\n");
		System.out.print("예매하신 영화의 좌석 번호는 ");
		Seat seat;
		for (int i = 0; i <= getForPrintSeat.size() - 1; i++) {
			seat = getForPrintSeat.get(i);
			System.out.printf("\n%d | %s | %s \n",seat.id,seat.movieTitle, seat.title);
		}
		System.out.println("입니다.\n");
		System.out.println();
		
		System.out.printf("취소할 영화 선택 :  ");
		
		int menu = sc.nextInt();
		
		Container.seatService.doDeleteSeat(menu);
		System.out.println("예매가 취소 되었습니다.");
	}

	private void showReviewList() {
		System.out.printf("1.리뷰 내역으로\n");
		System.out.printf("9. 이전으로");

		int menu = sc.nextInt();

		if (menu == 9) {
			return;
		}

		Member loginedMember = Container.getSession().getLoginedMember();
		List<Review> forPrintReview = Container.reviewService.getForPrintReviews(loginedMember.nickName);

		if (forPrintReview.size() == 0) {
			System.out.println("검색결과가 존재하지 않습니다.");
			return;
		}

		System.out.printf("=== === === 수정할 리뷰 선택 === === ===\n\n");
		System.out.println(" 번호 | 닉네임 | 평점 | 제목 ");

		Review review;
		for (int i = 0; i <= forPrintReview.size() - 1; i++) {
			if (forPrintReview.size() == 0) {
				System.out.println("리뷰 내역이 없습니다.");
				return;
			}

			review = forPrintReview.get(i);

			System.out.printf("%4d|%6s|%4.1f|%s|%s\n", review.id, review.name, review.grades, review.title,
					review.body);
		}

		System.out.println();

		System.out.printf("리뷰 번호 : ");
		int reviewId = sc.nextInt();

		Review choiceReview = Container.reviewService.getForPrintReview(reviewId);

		System.out.printf("평점 : ");
		float grades = sc.nextFloat();

		sc.nextLine();

		System.out.println("내용 : ");
		String body = sc.nextLine();

		reviewService.modifyReview(choiceReview.id, body, grades);

		System.out.println("리뷰 수정이 완료되었습니다.\n");
	}

	private void changeNickName() {
		Member loginedMember = Container.getSession().getLoginedMember();

		String nickName = null;

		System.out.printf("변경할 닉네임 입력 : ");
		nickName = sc.next();

		if (isJoinableNickName(nickName) == false) {
			System.out.println("이미 사용중인 닉네임 입니다.\n");
			return;
		}

		memberService.modifyNickName(nickName);
		System.out.println("\n닉네임이 변경 되었습니다.\n");
	}

	private void changeLoginPw() {
		Member loginedMember = Container.getSession().getLoginedMember();

		String loginedMemberLoginPwConfirm = null;
		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("현재 비밀번호 : ");
			loginedMemberLoginPwConfirm = sc.next();
			if (loginedMemberLoginPwConfirm.equals(loginedMember.loginPw) == false) {
				System.out.println("비밀번호를 다시 확인해주세요.\n");
				continue;
			}
			System.out.printf("변경할 비밀번호 : ");
			loginPw = sc.next();
			System.out.printf("변경할 비밀번호 확인 : ");
			loginPwConfirm = sc.next();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("변경할 비밀번호를 다시 입력해주세요.");
				continue;
			}
			break;
		}

		memberService.modifyLoginPw(loginPw);
		System.out.println("\n비밀번호가 변경 되었습니다.\n");
	}

	private void changeEmail() {
		Member loginedMember = Container.getSession().getLoginedMember();

		String eMail = null;

		while (true) {
			System.out.printf("변경할 Email : ");
			eMail = sc.next();

			if (isJoinableEmail(eMail) == false) {
				System.out.println("\n이미 사용중인 이메일 입니다.");
				continue;
			}
			break;
		}

		memberService.modifyEmail(eMail);
		System.out.println("\n이메일이 변경 되었습니다.\n");
	}
}