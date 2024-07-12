// end-point for get the dataset
const getURL = "/api/admin/revenue/";
// default config for line chart
let xValues = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
let yValues = [];
let config = {
  type: "line",
  data: {
    labels: xValues,
    datasets: [
      {
        labels: false,
        pointRadius: 3,
        borderColor: "rgba(255,0,0, 0.6)",
        data: yValues,
        fill: true,
        pointBackgroundColor: "rgba(255,0,0, 0.7)",
      },
    ],
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    plugins: {
      legend: {
        display: false,
      },
      title: {
        display: true,
        text: "",
        font: {
          size: 15,
        },
      },
    },
    scales: {
      x: {
        title: {
          display: true,
          text: "THÁNG",
          fontSize: 13,
        },
      },
      y: {
        title: {
          display: true,
          text: "Tổng doanh thu",
          fontSize: 13,
        },
      },
    },
  },
};

function updateDataset(chart, newData, year) {
  let dataset = Array(12).fill(0);
  let titleText = "KHÔNG CÓ DỮ LIỆU ĐỂ THỐNG KÊ";

  if (newData.length) {

    newData.forEach((item) => {
      let month = item.month;
      dataset[month - 1] = item.totalFees;
    });

    console.log(dataset);
    let revenue = dataset.reduce(
      (total, currentValue) => total + currentValue,
      0
    );

    titleText =
      "TỔNG DOANH THU TRONG NĂM " +
      year +
      ": " +
      revenue.toLocaleString("it-IT", { style: "currency", currency: "VND" });
  }

  chart.config.options.plugins.title.text = titleText;
  chart.config.data.datasets[0].data = dataset;
  chart.update();
}

// draw chart
var chart = new Chart($("#line-chart"), config);

// year picker
$(".yearpicker").yearpicker({
  year: new Date().getFullYear(),
  onChange: function (selectedYear) {
    fetch(getURL + selectedYear)
      .then((response) => {
        if (response.status === 200) {
          return response.json();
        } else {
          throw new Error("Failed to fetch data");
        }
      })
      .then((dataset) => {
        updateDataset(chart, dataset, selectedYear);
      })
      .catch((error) => {
        // window.location.reload();
        console.log(error);
      });
  },
});
