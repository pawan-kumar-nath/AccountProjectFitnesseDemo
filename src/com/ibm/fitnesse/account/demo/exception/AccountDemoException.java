package com.ibm.fitnesse.account.demo.exception;

import java.io.Serializable;

import com.ibm.fitnesse.account.demo.util.RestResponse;


public class AccountDemoException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// private members
    private int m_nErrorCode;       // Major error group
    private String m_sModule;           // Module/class where error occurred
    private String m_sMethod;           // Method where error occurred
    private String m_sComment;          // Programmer comment

    private static String newline = "\n";
    private static String indent = "    ";
    
	private RestResponse m_restResponse;

    public AccountDemoException() {
    }

    /**
     * Constructs a new McpException with a message.
     * @param  message - Message describing the exception.
     */
    public AccountDemoException(String message)
    {
    	m_nErrorCode = 0;
    	m_sComment=message;
    }

    /**
     * Construct a new McpException from a Throwable.
     *
     * @param  e - any Throwable
     */
    public AccountDemoException(Throwable e)
    {
        this(0, e.getMessage(),e);
    }

    /**
     * Construct a new McpException from a Message and a Throwable.
     *
     * @param  message - Message describing the exception.
     * @param  e - any Throwable
     */
    public AccountDemoException(String message, Throwable e)
    {
        this(0, message, e);
    }

    /**
     * Constructor - All other constructors that omit various arguments
     *                  reduce to a call to this constructor.
     *
     * @param a_nErrorCode Major error code
     * @param a_sModule Module/class where error occured
     * @param a_sMethod Method where error occured
     * @param a_sComment Programmer comment/description
     */
    public AccountDemoException(int a_nErrorCode , 
                       String a_sModule ,
                       String a_sMethod ,
                       String a_sComment)
    {
    	m_nErrorCode = a_nErrorCode;
        m_sModule = a_sModule;
        m_sMethod = a_sMethod;
        m_sComment = a_sComment;
    }

    public AccountDemoException(int a_nErrorCode ,
                       String a_sModule ,
                       String a_sMethod ,
                       String a_sComment,
                       Throwable cause)
    {
    	m_nErrorCode = a_nErrorCode;
        m_sModule = a_sModule;
        m_sMethod = a_sMethod;
        m_sComment = a_sComment;
        initCause(cause);
    }

    /**
     * This Constructor figures out the class and method that threw the exception 
     * so don't need to pass them
     *
     * @param a_nErrorCode Major error code
     * @param a_sComment Programmer comment/description
     */
    public AccountDemoException(int a_nErrorCode ,
                       String a_sComment)
    {
    	m_nErrorCode = a_nErrorCode;
        m_sComment = a_sComment;
        StackTraceElement[] stackTrace = this.getStackTrace();
        m_sModule = stackTrace[0].getClassName();
        m_sMethod = stackTrace[0].getMethodName();
    }


     public AccountDemoException(int errorCode ,
                       String comment,
                       Throwable throwable)
    {
        this(errorCode, comment);
        initCause(throwable);
    }
     
     public AccountDemoException(RestResponse a_restResponse, Throwable cause)
	 {
    	 m_restResponse = a_restResponse;
    	 initCause(cause);
	}
     
     public AccountDemoException(RestResponse a_restResponse,  String a_sComment)
	 {
    	 m_restResponse = a_restResponse;
         m_sComment = a_sComment;
	}

    /**
     * Overrides the standard getMessage, returning instead the comment text.
     *
     * @return The comment text, or the underlying exception message if no comment text exists.
     */
    public String getMessage()
    {
        return m_sComment;
    }

    /**
     * method getMajor - 'get' access method for member m_sMajor
     */
    public int getErrorCode()
    {
        return m_nErrorCode;
    }

    /**
     * method getModule - 'get' access method for member m_sModule
     */
    public String getModule()
    {
        return m_sModule;
    }

    /**
     * method getMethod - 'get' access method for member m_sMethod
     */
    public String getMethod()
    {
        return m_sMethod;
    }

    /**
     * method getComment - 'get' access method for member m_sComment
     */
    public String getComment()
    {
        return m_sComment;
    }
    
    public RestResponse getM_restResponse() {
		return m_restResponse;
	}

    /**
     * A static version of toString() that can be used in the
     *   super(message) call required of all constructors.
     */
    private static String toString(String a_comment)
    {
        StringBuffer sb = new StringBuffer();
        if (a_comment != null) {
            String[] comments = a_comment.split(newline);

            for (int i = 0; i < comments.length; i++) {
                String comment = comments[i];
                sb.append(newline).append(indent).append(comment);
            }
        }

        sb.append(newline);

        return sb.toString();
    }

    /**
     * Returns a formatted explanation of this exception condition.
     *
     * @return The formatted explanation of this exception condition.
     */
    public String toString()
    {
         return toString(m_sComment);
    }

   /**
    *  Re-throw this exception as an unchecked exception (@link RuntimeException).
    *  <p>
    *  This method is designed for use in methods whose signature does NOT
    *   include the "throws McpException", and CANNOT because the signature is
    *   set by some base class over which we have no control.
    */
    public void rethrowAsUnchecked()
    {
        throw new RuntimeException(this);
    }
}
