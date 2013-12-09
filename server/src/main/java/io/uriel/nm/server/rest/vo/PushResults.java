package io.uriel.nm.server.rest.vo;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.StringUtils;

public class PushResults
{
    private final ResultType resultType;
    
    private final String contents;
    
    private final ObjectMapper mapper;
    
    public PushResults(ResultType resultType, String contents)
    {
        super();
        this.resultType = resultType;
        this.contents = contents;
        this.mapper = new ObjectMapper();
    }
    
    /**
     * Converts current instance to it's JSON representation.
     * 
     * @return
     *      Current instance's representation in a JSON format. If any error
     *      happen during conversion, then the result will be a JSON with 
     *      problem's details.
     */
    public String toJson()
    {
        if (resultType != null && !StringUtils.isEmpty(contents))
        {
            try
            {
                String json = mapper.writeValueAsString(this);
                return json;
            } catch (Exception exp) {
                return writeError(exp.getMessage());
            }
        } else {
            return writeError("Type and contents must be filled.");
        }
    }
    
    /**
     * Helper method. Creates an error message without any possibility of
     * throwing a converter exception, by using only core elements.
     * 
     * @param message   Message describing the error.
     * @return          A simple JSON with the error message.
     */
    private String writeError(String message)
    {
        final StringBuilder error = new StringBuilder();
        error.append("{");
        error.append("\"resultType\":");
        error.append("\"");
        error.append(ResultType.ERROR);
        error.append("\"");
        error.append(",");
        error.append("\"description\":");
        error.append("\"");
        error.append(message);
        error.append("\"");
        error.append("}");
        return error.toString();
    }
    
    /** Enum providing possible result types. */
    public enum ResultType
    {
        /** Informs that a new item was added. */
        ADD_ITEM,
        
        /** Informs that an item was removed. */
        REMOVE_ITEM,
        
        /** Informs that an error has ocurred. */
        ERROR;
    }
}
