package org.jacorb.notification.filter.etcl;

/*
 *        JacORB - a free Java ORB
 *
 *   Copyright (C) 1999-2004 Gerald Brose
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Library General Public
 *   License as published by the Free Software Foundation; either
 *   version 2 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this library; if not, write to the Free
 *   Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 */

import org.jacorb.notification.filter.EvaluationContext;
import org.jacorb.notification.filter.DynamicEvaluator;
import org.jacorb.notification.filter.EvaluationException;
import org.omg.CORBA.Any;
import org.omg.DynamicAny.DynAnyFactoryPackage.InconsistentTypeCode;
import org.omg.DynamicAny.DynAnyPackage.InvalidValue;
import org.omg.DynamicAny.DynAnyPackage.TypeMismatch;

import antlr.Token;
import org.jacorb.notification.filter.EvaluationResult;

/** A simple node to represent IN operation */
public class InOperator extends AbstractTCLNode {

    public InOperator(Token tok) {
        super(tok);
    }

    public String toString() {
        return " in";
    }

    public String getName() {
        return getClass().getName();
    }

    public EvaluationResult evaluate(EvaluationContext context)
        throws EvaluationException {

        Any _right = right().evaluate(context).getAny();
        EvaluationResult _l = left().evaluate(context);

        DynamicEvaluator _evaluator = context.getDynamicEvaluator();
        return _evaluator.evaluateElementInSequence(context, _l, _right);
    }

    public void acceptInOrder(AbstractTCLVisitor visitor) throws VisitorException {
        left().acceptInOrder(visitor);
        visitor.visitIn(this);
        right().acceptInOrder(visitor);
    }

    public void acceptPreOrder(AbstractTCLVisitor visitor) throws VisitorException {
        visitor.visitIn(this);
        left().acceptPreOrder(visitor);
        right().acceptPreOrder(visitor);
    }

    public void acceptPostOrder(AbstractTCLVisitor visitor) throws VisitorException {
        left().acceptPostOrder(visitor);
        right().acceptPostOrder(visitor);
        visitor.visitIn(this);
    }
}
