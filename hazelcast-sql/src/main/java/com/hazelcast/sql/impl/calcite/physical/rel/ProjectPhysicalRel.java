package com.hazelcast.sql.impl.calcite.physical.rel;

import org.apache.calcite.plan.RelOptCluster;
import org.apache.calcite.plan.RelOptCost;
import org.apache.calcite.plan.RelOptPlanner;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.core.Project;
import org.apache.calcite.rel.metadata.RelMetadataQuery;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rex.RexNode;

import java.util.List;

/**
 * Physical projection.
 */
public class ProjectPhysicalRel extends Project implements PhysicalRel {
    public ProjectPhysicalRel(
        RelOptCluster cluster,
        RelTraitSet traits,
        RelNode input,
        List<? extends RexNode> projects,
        RelDataType rowType
    ) {
        super(cluster, traits, input, projects, rowType);
    }

    @Override
    public Project copy(RelTraitSet traitSet, RelNode input, List<RexNode> projects, RelDataType rowType) {
        return new ProjectPhysicalRel(getCluster(), traitSet, input, projects, rowType);
    }

    @Override
    public void visit(PhysicalRelVisitor visitor) {
        ((PhysicalRel)input).visit(visitor);

        visitor.onProject(this);
    }

    @Override
    public RelOptCost computeSelfCost(RelOptPlanner planner, RelMetadataQuery mq) {
        return super.computeSelfCost(planner, mq);
    }
}
