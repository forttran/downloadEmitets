/*����� "�������" ������ ��� ���������� ����������� ������� �������� ��������� � ���� �����*/
package firstProject;

public class CreateStructureEmitets {
	//��� ������ ��� �������� ���������
	httpRequest date = new httpRequest("http://www.finam.ru/cache/icharts/icharts.js");
	MSSQLDATE Dates = new MSSQLDATE();
	Dates.createStructure(date.preloader(date.emitetsRead()));
}
