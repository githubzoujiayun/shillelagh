package shillelagh.internal;

import java.util.Date;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

/** Represents the data for a column in a database and mapping it back to its java counter part */
class TableColumn {

  private final ShillelaghLogger logger;
  private final SqliteType type;
  private final String columnName;
  private final Element element;

  TableColumn(Element element, ShillelaghLogger logger) {
    this.columnName = element.getSimpleName().toString();
    this.logger = logger;
    this.element = element;

    TypeMirror typeMirror = element.asType();
    this.type = SqliteType.from(typeMirror);
    logger.d("Element " + element + " Type " + typeMirror.toString());
  }

  String getColumnName() {
    return columnName;
  }

  SqliteType getSqlType() {
    return type;
  }

  String getType()  {
    return element.asType().toString();
  }

  boolean isDate() {
    return getType().equals(Date.class.getName());
  }

  boolean isBoolean() {
    final String typeString = getType();
    return typeString.equals(boolean.class.getName()) || typeString.equals(Boolean.class.getName());
  }

  @Override public String toString() {
    return columnName + " " + type.toString();
  }
}