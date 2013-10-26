package dataset.ics;
import java.io.File;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.Codecs.DocumentType;
import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.BoundString;
import org.codehaus.preon.annotation.Purpose;

public class PrObjType {
	public final static String FILE_NAME = "probjtyp.dat";
	
	@Purpose(value="The number of classes")
	@BoundNumber()
    private int numVarFiles;
    
	@Purpose(value="An array of all classes in the model")
	@BoundList(size="numVarFiles", type=_Class.class)
	private _Class[] clazzes;
	
	public int getNumVarFiles() {
		return numVarFiles;
	}

	public _Class[] getClazzes() {
		return clazzes;
	}
	
	@Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this);
	}
	
	class _Class {
		@BoundNumber()
	    private int nameLength;
		
		@BoundString(size="nameLength") 
		private String nameString;
		
		@BoundNumber()
	    private int recWidth;
		
		@BoundNumber()
	    private int numberParents;
		
		@BoundList(type=int.class, size="numberParents")
		private int[] parents;
		
		@BoundNumber()
	    private int PrPtrsOffset;
		
		@BoundNumber()
		private int behaviors;
		
		@BoundNumber()
		private int styles;
		
		@BoundNumber()
		private int scope;
		
		@BoundNumber()
		private int siblingScope;
		
		@BoundNumber()
		private int objectNamePointer;
		
		@BoundNumber()
		private int objectId;
		
		@BoundNumber()
		private int generated;
		
		@BoundNumber()
		private int numAttributes;
		
		@BoundList(size="numAttributes", type=_Attribute.class)
		private _Attribute[] attributes;
		
		@Override
		public String toString() {
		    return ToStringBuilder.reflectionToString(this);
		}
		
		class _Attribute {
			@Purpose(value="Stores something")
			@BoundNumber()
			private int offset;

			@BoundNumber()
			/** java doc below */
			private int namePointer;

			@BoundNumber()
			private int attrBehaviorIndex;

			@BoundNumber()
			private int attrFlags;

			@BoundNumber()
			private int attrDataType;

			@BoundNumber()
			private int attrPointerType;

			@BoundNumber()
			private int attrSpecification;

			@BoundNumber()
			private int reqForBehaviorId;

			@BoundNumber()
			private int reqId;
			
			@BoundNumber()
			private int attrMapId;
			
			@BoundNumber()
			private int attrAlterMin;
			
			@BoundNumber()
			private int attrAlterMax;
			
			@BoundNumber()
			private int attrAlterChoices;
			
			@Override
			public String toString() {
			    return ToStringBuilder.reflectionToString(this);
			}
		}
	}
	public static void main(String[] args) throws Exception {
		Codecs.document(Codecs.create(PrObjType.class), DocumentType.Html, new File("doc/probjtype.html"));
	}
}
