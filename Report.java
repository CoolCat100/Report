import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Report {
    public static void main(String[] args) {

        Report report1 = new Report(1l, "no_punkz", 1, LocalDate.of(2021, 9, 25), "Из-за внешних факторов все никак не могу сделать " +
                "задачу" + " со стримами, не хватило времени");
        Report report2 = new Report(2l, "no_punkz", 5, LocalDate.of(2021, 9, 27), "Продолжаю мучать предпоследнюю задачу (теперь и " +
                "Толяна), собес на котором узнал много нового");
        Report report3 = new Report(3l, "no_punkz", 2, LocalDate.of(2021, 9, 26), "Бился над компаратором, пытался накостылить через " +
                "видимую внешнюю переменную, начал читать о решениях из чата");
        Report report4 = new Report(4l, "no_punkz", 6, LocalDate.of(2021, 9, 23), "тестовый");
        Report report5 = new Report(5l, "no_punkz", 3, LocalDate.of(2021, 9, 24), "тестовый2");
        Report report6 = new Report(6l, "no_punkz", 4, LocalDate.of(2021, 9, 19), "тестовый2");
        Report report7 = new Report(7l, "alekshas", 2, LocalDate.of(2021, 9, 25), "тестовый леха");
        Report report8 = new Report(8l, "alekshas", 1, LocalDate.of(2021, 9, 24), "тестовый лех2");
        List<Report> list = new ArrayList<>();
        list.add(report1);
        list.add(report2);
        list.add(report3);
        list.add(report4);
        list.add(report5);
        list.add(report6);
        list.add(report7);
        list.add(report8);
        long startTime = System.currentTimeMillis();
        String result = "";
        for (int i = 0; i < 10000000; i++) {
            result = reportHistory(list, "no_punkz", 3);
        }
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        double medium = (double) timeElapsed / 10000000;
        System.out.println(medium);
        System.out.println(result);
//        System.out.println(reportHistory(list, "no_punkz", 3));
    }

    Report(Long id, String studentUserName, Integer hours, LocalDate date, String title) {
        this.id = id;
        this.studentUserName = studentUserName;
        this.hours = hours;
        this.date = date;
        this.title = title;
    }

    private Long id;

    private String studentUserName;

    private Integer hours;

    private LocalDate date;

    private String title;

//    public static String reportHistory(List<Report> reports, String studentUserName, int count) {
//        return reports.stream()
//                .filter(x -> x.getStudentUserName().equals(studentUserName))
//                .sorted(Comparator.comparing(Report::getDate).reversed())
//                .limit(count)
//                .sorted(Comparator.comparing(Report::getDate))
//                .map(x -> x.getStudentUserName() + "\n" + x.getDate().toString() + "\n"
//                        + x.getHours().toString() + "\n" + x.getTitle() + "\n")
//                .collect(Collectors.joining("-----------------" + "\n"));
//    }
    public static String reportHistory(List<Report> reports, String studentUserName, int count) {
        List<Report> sortReports = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reports.size(); i++) {
            if (reports.get(i).getStudentUserName().equals(studentUserName)) {
                sortReports.add(reports.get(i));
            }
        }
        Collections.sort(sortReports, Comparator.comparing(Report ::getDate).reversed());
        for (int i = sortReports.size() - 1; i > count - 1; i--) {
            sortReports.remove(i);
        }
        Collections.sort(sortReports, Comparator.comparing(Report ::getDate));
        for (int i = 0; i < sortReports.size(); i++) {
            result.append(sortReports.get(i).getStudentUserName()).append("\n").append(sortReports.get(i).getDate().toString()).append("\n").append(sortReports.get(i).getHours().toString()).append("\n").append(sortReports.get(i).getTitle()).append("\n");
        }
        return result.toString();
    }

    public String getStudentUserName() {
        return studentUserName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getHours() {
        return hours;
    }

    public String getTitle() {
        return title;
    }
}

