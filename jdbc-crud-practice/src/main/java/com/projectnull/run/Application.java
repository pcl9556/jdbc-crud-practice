package com.projectnull.run;

import com.projectnull.dao.ClinicDAO;
import com.projectnull.dto.ClinicDTO;
import com.projectnull.dto.ClinicDetailDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static com.projectnull.common.JDBCTemplate.getConnection;
import static com.projectnull.common.JDBCTemplate.close;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClinicDAO dao = new ClinicDAO();

        // 연결 한 번만 열어두고, 끝에서 닫기
        Connection con = getConnection();


        while (true) {
            System.out.println("\n=== Clinic CRUD ===");
            System.out.println("1. 전체 조회 (JOIN 포함)");
            System.out.println("2. 단건 조회 (JOIN 포함)");
            System.out.println("3. 등록 (Create)");
            System.out.println("4. 수정 (Update)");
            System.out.println("5. 삭제 (Delete)");
            System.out.println("0. 종료");
            System.out.print("번호를 선택 하세요 >> ");

            String sel = sc.nextLine().trim();
            if ("0".equals(sel)) break;

            try {
                switch (sel) {
                    case "1": { // Read All (JOIN)
                        List<ClinicDetailDTO> list = dao.selectAllClinicsDetail(con);


                        if (list.isEmpty()) System.out.println("데이터가 없습니다.");
                        else {
                            for (ClinicDetailDTO d : list) {
                                System.out.println(d);
                            }
                        }
                        break;
                    }
                    case "2": { // Read One (JOIN)
                        System.out.print("조회할 진료코드 >> ");
                        int code = Integer.parseInt(sc.nextLine().trim());
                        ClinicDetailDTO d = dao.selectClinicDetailByCode(con, code);
                        System.out.println(d == null ? "해당 진료가 없습니다." : d.toString());
                        break;
                    }
                    case "3": { // Create
                        System.out.print("진료코드(PK) >> ");
                        int clinicCode = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("의사코드 >> ");
                        int doctorCode = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("환자코드 >> ");
                        int patientCode = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("처방코드(VARCHAR(3), 없으면 빈칸) >> ");
                        String pres = sc.nextLine().trim();
                        String prescriptionCode = pres.isEmpty() ? null : pres;

                        int rows = dao.insertClinic(con,
                                new ClinicDTO(clinicCode, doctorCode, patientCode, prescriptionCode));
                        System.out.println(rows > 0 ? "등록 완료" : "등록 실패");
                        break;
                    }
                    case "4": { // Update
                        System.out.print("수정할 진료코드(PK) >> ");
                        int clinicCode = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("변경 의사코드 >> ");
                        int doctorCode = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("변경 환자코드 >> ");
                        int patientCode = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("변경 처방코드(정수, 없으면 빈칸) >> ");
                        String pres = sc.nextLine().trim();
                        String prescriptionCode = pres.isEmpty() ? null : pres;

                        int rows = dao.updateClinic(con,
                                new ClinicDTO(clinicCode, doctorCode, patientCode, prescriptionCode));
                        System.out.println(rows > 0 ? "수정 완료" : "수정 실패");
                        break;
                    }
                    case "5": { // Delete (ALL-IN-ONE)
                        System.out.print("삭제할 진료코드 >> ");
                        int clinicCode = Integer.parseInt(sc.nextLine().trim());
                        int rows = dao.deleteClinicAllInOne(con, clinicCode);
                        System.out.println(rows > 0 ? "삭제 완료" : "삭제 실패");
                        break;
                    }

                    default:
                        System.out.println("메뉴를 다시 선택하세요.");
                }
            } catch (Exception e) {
                System.out.println("오류: " + e.getMessage());
            }
        }


        close(con);
        sc.close();
        System.out.println("종료!");
    }
}

