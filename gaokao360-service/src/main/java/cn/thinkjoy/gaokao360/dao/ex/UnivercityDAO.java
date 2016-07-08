package cn.thinkjoy.gaokao360.dao.ex;

import java.util.List;
import java.util.Map;

public interface UnivercityDAO {


	public void insertMajorEnBatch(List<Map<String, Object>> list);

	public void insertMajorPlanEnBatch(List<Map<String, Object>> list);

	public void insertUniversityEnBatch(List<Map<String, Object>> list);

	public void insertSuanfaData_hb_1_w(List<Map<String, Object>> list);
	void cleanSuanfaData_hb_1_w();
	void updateUniversityId();
	void cleanUniversityData();
	void cleanMajorPlanData();
	void cleanMajorData();

	//	����ԺУרҵ�����ƻ�����
	void updateUniversityIdToImport();
	void updateMajorIdToImport();
	void updateBatch1();
	void updateBatch2();
	void updateBatch3();
	void updateBatch4();
	void updateBatch5();
	void updateBatch6();
	void updateBatch7();
	void updateBatch8();
	void updateBatch9();
	void updateBatch10();
	void updateBatch11();
	List<String> getUniversityNameIsNull();
	void truncateUniq();
	void insertImportToUniq();
	void insertMajorPlanToFormal();
	void insertUniversityPlanToFormal();

	//ԺУ¼ȡ��������
	void updateUniversityIdToImportMajor();
	void updateMajorIdToImportMajor();
	void getUniversityNameIsNullMajor();
	void insertImportToMajorFormal();

	//ԺУ¼ȡ��������
	void updateUniversityIdToImportUniversity();
	void getUniversityNameIsNullUniversity();
	void insertImportToUniversityFormal();


}
