package com.szczepix.myinvest.utils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SQLiteDialectUtilsTest {

    private SQLiteDialectUtils dialect;

    @Before
    public void setUp() {
        dialect = new SQLiteDialectUtils();
    }

    @Test
    public void creation() {
        assertThat(dialect).isNotNull();
    }

    @Test
    public void supportsIdentityColumns() {
        assertThat(dialect.supportsIdentityColumns()).isTrue();
    }

    @Test
    public void hasDataTypeInIdentityColumn() {
        assertThat(dialect.hasDataTypeInIdentityColumn()).isFalse();
    }

    @Test
    public void getIdentityColumnString() {
        assertThat(dialect.getIdentityColumnString()).isEqualToIgnoringCase("integer");
    }

    @Test
    public void getIdentitySelectString() {
        assertThat(dialect.getIdentitySelectString()).isEqualToIgnoringCase("select last_insert_rowid()");
    }

    @Test
    public void supportsLimit() {
        assertThat(dialect.supportsLimit()).isTrue();
    }

    @Test
    public void getLimitString() {
        assertThat(dialect.getLimitString("query", true)).isEqualToIgnoringCase("query limit ? offset ?");
        assertThat(dialect.getLimitString("query", false)).isEqualToIgnoringCase("query limit ?");
    }

    @Test
    public void supportsTemporaryTables() {
        assertThat(dialect.supportsTemporaryTables()).isTrue();
    }

    @Test
    public void getCreateTemporaryTableString() {
        assertThat(dialect.getCreateTemporaryTableString()).isEqualToIgnoringCase("create temporary table if not exists");
    }

    @Test
    public void dropTemporaryTableAfterUse() {
        assertThat(dialect.dropTemporaryTableAfterUse()).isFalse();
    }

    @Test
    public void supportsCurrentTimestampSelection() {
        assertThat(dialect.supportsCurrentTimestampSelection()).isTrue();
    }

    @Test
    public void isCurrentTimestampSelectStringCallable() {
        assertThat(dialect.isCurrentTimestampSelectStringCallable()).isFalse();
    }

    @Test
    public void getCurrentTimestampSelectString() {
        assertThat(dialect.getCurrentTimestampSelectString()).isEqualToIgnoringCase("select current_timestamp");
    }

    @Test
    public void supportsUnionAll() {
        assertThat(dialect.supportsUnionAll()).isTrue();

    }

    @Test
    public void hasAlterTable() {
        assertThat(dialect.hasAlterTable()).isFalse();
    }

    @Test
    public void dropConstraints() {
        assertThat(dialect.dropConstraints()).isFalse();
    }

    @Test
    public void getAddColumnString() {
        assertThat(dialect.getAddColumnString()).isEqualToIgnoringCase("add column");
    }

    @Test
    public void getForUpdateString() {
        assertThat(dialect.getForUpdateString()).isEmpty();
    }

    @Test
    public void supportsOuterJoinForUpdate() {
        assertThat(dialect.supportsOuterJoinForUpdate()).isFalse();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getDropForeignKeyString() throws Exception {
        dialect.getDropForeignKeyString();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getAddForeignKeyConstraintString() throws Exception {
        dialect.getAddForeignKeyConstraintString("", new String[]{}, "", new String[]{}, false);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getAddPrimaryKeyConstraintString() throws Exception {
        dialect.getAddPrimaryKeyConstraintString("");
    }

    @Test
    public void supportsIfExistsBeforeTableName() {
        assertThat(dialect.supportsIfExistsBeforeTableName()).isTrue();
    }

    @Test
    public void supportsCascadeDelete() {
        assertThat(dialect.supportsCascadeDelete()).isFalse();
    }

}