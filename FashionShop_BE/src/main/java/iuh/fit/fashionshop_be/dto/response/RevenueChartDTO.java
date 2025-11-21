package iuh.fit.fashionshop_be.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueChartDTO {
    private List<ChartDataPoint> daily;
    private List<ChartDataPoint> weekly;
    private List<ChartDataPoint> monthly;
    private List<ChartDataPoint> yearly;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChartDataPoint {
        private String label; // ngày, tuần, tháng, năm
        private BigDecimal revenue;
        private Long orderCount;
    }
}