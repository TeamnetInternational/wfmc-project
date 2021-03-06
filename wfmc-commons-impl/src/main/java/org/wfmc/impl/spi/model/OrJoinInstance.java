/*--

 Copyright (C) 2002-2005 Adrian Price.
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows
    these conditions in the documentation and/or other materials
    provided with the distribution.

 3. The names "OBE" and "Open Business Engine" must not be used to
 	endorse or promote products derived from this software without prior
 	written permission.  For written permission, please contact
 	adrianprice@sourceforge.net.

 4. Products derived from this software may not be called "OBE" or
 	"Open Business Engine", nor may "OBE" or "Open Business Engine"
 	appear in their name, without prior written permission from
 	Adrian Price (adrianprice@users.sourceforge.net).

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 POSSIBILITY OF SUCH DAMAGE.

 For more information on OBE, please see
 <http://obe.sourceforge.net/>.

 */

package org.wfmc.impl.spi.model;

/**
 * Implements the logic and state of an XOR-JOIN instance.
 *
 * @author Adrian Price
 */
public final class OrJoinInstance extends JoinInstance {
    private static final long serialVersionUID = 8542861376795573396L;

    /**
     * Constructs a new XOR-join instance.
     *
     * @param transitions IDs of afferent transitions.
     */
    public OrJoinInstance(String[] transitions) {
        super(transitions);
    }

    /**
     * Handles the activation of an afferent transition.  An XOR-join fires as
     * soon as one its afferent transitions has fired; subsequent activations
     * of any of the transitions are ignored until the join is {@link #reset}.
     *
     * @param transitionId The ID of the transition that fired.
     * @return <code>true</code> if the join fired.
     */
    public synchronized boolean shouldFire(String transitionId) {
        // Only fire once until reset.
        boolean fire = false;
        if (!hasFired()) {
            for (int i = 0; i < _transitions.length; i++) {
                if (_transitions[i].equals(transitionId)) {
                    if (didFire(i)) {
                        fire();
                        fire = true;
                    }
                    break;
                }
            }
        }
        return fire;
    }
}