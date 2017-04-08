package org.nd4j.autodiff.autodiff;

import java.util.List;

import org.nd4j.autodiff.Field;
import org.nd4j.autodiff.graph.graph.Graph;
import org.nd4j.autodiff.opstate.NDArrayInformation;
import org.nd4j.autodiff.opstate.OpState;


public class Sum<X extends Field<X>> extends AbstractBinaryFunction<X> {

    public Sum(Graph<NDArrayInformation,OpState> graph,DifferentialFunction<X> i_v1, DifferentialFunction<X> i_v2) {
        super(graph,i_v1, i_v2);
    }

    @Override
    public X doGetValue() {
        return larg().getValue().plus(rarg().getValue());
    }

    @Override
    public double getReal() {
        return larg().getReal() + rarg().getReal();
    }

    @Override
    public DifferentialFunction<X> diff(Variable<X> i_v1) {
        return (larg() == rarg()) ? larg().diff(i_v1).mul(2L) // Field is
                                                              // commutative
                                                              // with respect to
                                                              // addition.
                : larg().diff(i_v1).plus(rarg().diff(i_v1));
    }

    @Override
    public String toString() {
        return "(" + larg().toString() + "+" + rarg().toString() + ")";
    }

    @Override
    public String doGetFormula(List<Variable<X>> variables) {
        return "(" + larg().doGetFormula(variables) + "+" + rarg().doGetFormula(variables) + ")";
    }
}
