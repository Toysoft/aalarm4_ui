package net.kprod.aalarmui.bean;

import java.util.List;

public class DataTableData {
    private int draw;
    private int recordsTotal;
		private int recordsFiltered;

		private List data;

		public List getData() {
			return data;
		}

		public void setData(List data) {
			this.data = data;
		}

		public int getDraw() {
			return draw;
		}

		public void setDraw(int draw) {
			this.draw = draw;
		}

		public int getRecordsTotal() {
			return recordsTotal;
		}

		public void setRecordsTotal(int recordsTotal) {
			this.recordsTotal = recordsTotal;
		}

		public int getRecordsFiltered() {
			return recordsFiltered;
		}

		public void setRecordsFiltered(int recordsFiltered) {
			this.recordsFiltered = recordsFiltered;
		}
	}