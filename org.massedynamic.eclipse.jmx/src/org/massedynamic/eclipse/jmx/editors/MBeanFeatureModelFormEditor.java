/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.massedynamic.eclipse.jmx.editors;

import org.massedynamic.eclipse.core.editors.DataModelFormEditor;
import org.massedynamic.eclipse.core.viewers.DataModelElementType;
import org.massedynamic.eclipse.jmx.model.MBeanFeatureModel;
import org.massedynamic.eclipse.jmx.viewers.JmxConnectionModelElementType;
import org.massedynamic.eclipse.jmx.viewers.MBeanModelElementType;

/**
 * TODO: Comment.
 * 
 * @author Mark Masse
 */
public abstract class MBeanFeatureModelFormEditor<M extends MBeanFeatureModel<M, ?>> extends DataModelFormEditor<M> {

    @Override
    protected DataModelElementType getOwnerModelElementType() {
        return new JmxConnectionModelElementType();
    }

    @Override
    protected DataModelElementType getParentModelElementType() {
        return new MBeanModelElementType();
    }

}
