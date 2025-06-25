import java.util.Scanner;

public class RegistrationSystem {
    private final Scanner scanner = new Scanner(System.in);
    private final int MAX_MEMBER_NUM = 10000;
    private final Member[] members = new Member[MAX_MEMBER_NUM];
    private int memberCount = 0;

    public void start() {
        while (true) {
            printMenu();

            System.out.println("원하시는 기능의 숫자를 입력하세요.");

            int choice = inputInt();

            switch (choice) {
                case 1 -> addMember();
                case 2 -> clear();
                case 3 -> printAll();
                case 4 -> {
                    System.out.println("시스템을 종료합니다.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("잘못된 숫자입니다. 다시 선택해주세요.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n========== 회원 등록 시스템 ==========");
        System.out.println("1. 회원 추가");
        System.out.println("2. 전체 삭제");
        System.out.println("3. 전체 회원 출력");
        System.out.println("4. 종료");
        System.out.println("=====================================");
    }

    private void addMember() {
        if (memberCount >= MAX_MEMBER_NUM) {
            System.out.println("더 이상 등록할 수 없습니다. (최대 " + MAX_MEMBER_NUM + "명)");
            return;
        }

        System.out.print("이름 입력: ");
        String name = scanner.nextLine();

        System.out.print("나이 입력: ");
        int age = inputInt();

        System.out.print("전화번호 입력: (XXX-XXXX-XXXX) 형태로 입력");
        String phone = inputPhoneNumber();

        Member member = new Member(name, age, phone);
        members[memberCount++] = member;
        System.out.println("회원이 등록되었습니다.");
    }

    private void clear() {
        for (int i = 0; i < memberCount; i++) {
            members[i] = null;
        }
        memberCount = 0;
        System.out.println("모든 회원 정보가 삭제되었습니다.");
    }

    private void printMember(Member member) {
        System.out.println("이름 : " + member.getName() +
                "   나이 : " + member.getAge() +
                "   전화번호 : " + member.getPhoneNumber());
    }

    private void printAll() {
        if (memberCount == 0) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        System.out.println("\n총 " + memberCount + "명의 회원 목록:\n");
        for (int i = 0; i < memberCount; i++) {
            System.out.print("[" + (i + 1) + "] ");
            printMember(members[i]);
        }
    }

    private int inputInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }

    private String inputPhoneNumber() {
        while (true) {
            try {
                String phoneNumber = scanner.nextLine();

                String[] splitedNumber = phoneNumber.split("-");

                if (splitedNumber.length != 3) {
                    throw new IllegalArgumentException();
                }

                for (String number : splitedNumber) {
                    Integer.parseInt(number);
                }

                return phoneNumber;
            } catch (IllegalArgumentException e) {
                System.out.println("맞는 형식으로 입력해주세요. (XXX-XXXX-XXXX) 형태로 입력");
            }
        }
    }
}
