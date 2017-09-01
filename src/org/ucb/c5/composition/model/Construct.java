package org.ucb.c5.composition.model;

import java.util.List;

/**
 * Encodes a genetic construct described in terms of a single operon
 * encoding multiple cocistronic transcripts
 * 
 * @author J. Christopher Anderson
 */
public class Construct {
    public List<Transcript> mRNAs;
    public String promoter;
    public String terminator;
    
    public String toSeq() throws Exception {
        StringBuilder out = new StringBuilder();
        out.append(promoter);
        for(Transcript mrna : mRNAs) {
            out.append(mrna.toSeq());
        }
        out.append(terminator);
        return out.toString();
    }
}
