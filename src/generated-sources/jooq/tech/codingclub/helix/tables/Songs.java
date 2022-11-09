/**
 * This class is generated by jOOQ
 */
package tech.codingclub.helix.tables;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value    = { "http://www.jooq.org", "3.3.2" },
                            comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Songs extends org.jooq.impl.TableImpl<tech.codingclub.helix.tables.records.SongsRecord> {

	private static final long serialVersionUID = 1610305697;

	/**
	 * The singleton instance of <code>public.Songs</code>
	 */
	public static final tech.codingclub.helix.tables.Songs SONGS = new tech.codingclub.helix.tables.Songs();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<tech.codingclub.helix.tables.records.SongsRecord> getRecordType() {
		return tech.codingclub.helix.tables.records.SongsRecord.class;
	}

	/**
	 * The column <code>public.Songs.name</code>.
	 */
	public final org.jooq.TableField<tech.codingclub.helix.tables.records.SongsRecord, java.lang.String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * The column <code>public.Songs.url</code>.
	 */
	public final org.jooq.TableField<tech.codingclub.helix.tables.records.SongsRecord, java.lang.String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR, this, "");

	/**
	 * Create a <code>public.Songs</code> table reference
	 */
	public Songs() {
		this("Songs", null);
	}

	/**
	 * Create an aliased <code>public.Songs</code> table reference
	 */
	public Songs(java.lang.String alias) {
		this(alias, tech.codingclub.helix.tables.Songs.SONGS);
	}

	private Songs(java.lang.String alias, org.jooq.Table<tech.codingclub.helix.tables.records.SongsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Songs(java.lang.String alias, org.jooq.Table<tech.codingclub.helix.tables.records.SongsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, tech.codingclub.helix.Public.PUBLIC, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public tech.codingclub.helix.tables.Songs as(java.lang.String alias) {
		return new tech.codingclub.helix.tables.Songs(alias, this);
	}

	/**
	 * Rename this table
	 */
	public tech.codingclub.helix.tables.Songs rename(java.lang.String name) {
		return new tech.codingclub.helix.tables.Songs(name, null);
	}
}
